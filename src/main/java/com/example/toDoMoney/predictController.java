package com.example.toDoMoney;

import com.example.toDoMoney.Categories.Categories;
import com.example.toDoMoney.MoneyFlow.MoneyFlow;
import com.example.toDoMoney.Predict.Predict;
import com.example.toDoMoney.Predict.PredictDTO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.toDoMoney.Predict.PredictDTO.createPredictDTO;

@RestController
public class predictController {
    @Autowired
    PredictRepo predictRepo;
    @Autowired
    MoneyFlowRepo moneyFlowRepo;
    @Autowired
    CategoriesRepo categoriesRepo;

    public List<PredictDTO> monthlySetUp(int year, int month) {
        List<Object[]> results = moneyFlowRepo.getMonthlyCostByCategory(year, month); //쿼리문이 월별,카테고리별이기 때문에 월중에 카테고리가 있는것만 하나의 레코드가되고 참조자가 된다.

        List<Predict> existingPredicts=predictRepo.findByYearAndMonth(year,month); // 월의 리스트를 가져옴
        List<Predict> spendingList = new ArrayList<>(existingPredicts);

        results.forEach(row -> {
            String dateStr = row[0].toString();
            String[] parts = dateStr.split("-");

            int year_p = Integer.parseInt(parts[0]);
            int month_p = Integer.parseInt(parts[1]);
            Long categoryId = ((Number) row[1]).longValue();
            Categories category = categoriesRepo.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("category not found: " + categoryId));

            int totalCost = ((Number) row[2]).intValue();

            Predict existingPredict = spendingList.stream() //stream() 새로운 데이터의 흐름을 생성한다.
                    .filter(p -> p.getYear() == year_p &&
                            p.getMonth() == month_p &&
                            p.getCategory().equals(category))
                    .findFirst()
                    .orElse(null);

            if (existingPredict != null) {
                existingPredict.setMonthCost(totalCost);
            } else {
                spendingList.add(new Predict(  // 년월을 int로 주고 ,카테고리는 참조자 만들어주고
                        null,
                        year_p,
                        month_p,
                        category,
                        0,  // predict와 cost는 원래 값이 있다면 갱신해야함
                        ((Number) row[2]).intValue()
                ));
            }
        });
        predictRepo.saveAll(spendingList);
        List<PredictDTO> dtoList = new ArrayList<>();
        for(Predict p: spendingList){
            PredictDTO dto=createPredictDTO(p);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<PredictDTO> updateOrCreatePredicts(List<PredictDTO> predictList) {
        int year = 0,month=0;
        for (PredictDTO dto : predictList) {
            System.out.println(dto.toString());
            Long categoryId = dto.getCategoryId(); // 1,2,3,4,5,6,7
            Categories category = categoriesRepo.findById(categoryId)
                    .orElseThrow(() -> new IllegalArgumentException("카테고리 없음" + categoryId));

            Optional<Predict> existingPredict = predictRepo.findByYearAndMonthAndCategory(dto.getYear(), dto.getMonth(), category);

            if (existingPredict.isPresent()) { //Optional의 함수인 isPresent는 값이 있는지에따라 true,false를 반환한다.
                Predict predict = existingPredict.get(); //get은 값이 존재하면 그 객체를 반환함
                predict.setPredict(dto.getPredict());
                predictRepo.save(predict);
            } else {
                Predict newPredict = new Predict(null, dto.getYear(), dto.getMonth(),category, dto.getPredict(), dto.getMonthCost());
                predictRepo.save(newPredict);
            }
            year=dto.getYear();
            month=dto.getMonth();
        }
        List<Predict> updatedList=predictRepo.findByYearAndMonth(year,month);
        List<PredictDTO> updatedDTOList= new ArrayList<>();
        for(Predict p:updatedList){
            PredictDTO updatedDTO =createPredictDTO(p);
            updatedDTOList.add(updatedDTO);
        }
        return updatedDTOList;
    }

    @GetMapping("/getMonthlyCost/{year}/{month}")
    public ResponseEntity<List<PredictDTO>> setupMonthlyCost(
            @PathVariable int year,
            @PathVariable int month) {
        List<PredictDTO> predictDTOList = monthlySetUp(year, month); //선택한 월의 프레딕트 리스트를 만들것이다.
        return ResponseEntity.ok(predictDTOList);
    }

    @PostMapping("/updatePredict")   //월에 해당하는 Predict를 리스트로 받아서
    public ResponseEntity<List<PredictDTO>> Predicts(@RequestBody List<PredictDTO> predictList) {
        System.out.println("hihihi"+predictList);
        List<PredictDTO> updatedPredict=updateOrCreatePredicts(predictList); // 리스트중 하나의 객체에 카테고리의 번호가 비어있는지 여부에따라 update, create를 분기할 것임
        return (updatedPredict!=null) ?
                ResponseEntity.status(HttpStatus.OK).body(updatedPredict):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/getCategories")
    public List<Categories> getCategories() {
        return categoriesRepo.findAll();
    }


}


interface PredictRepo extends JpaRepository<Predict, Long> {
    Optional<Predict> findByYearAndMonthAndCategory(int year, int month, Categories category);
    List<Predict> findByYearAndMonth(int year, int month);
}
