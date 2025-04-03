package com.example.todomoney;

import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {
    public static Context context;

    static String formatting(int price) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.KOREA);
        return format.format(price);
    }

    private CalendarView calendarView;

    public ActivityResultLauncher<Intent> getLauncher() {
        return launcher;
    }

    ActivityResultLauncher<Intent> launcher;
    String selectDate;


    private RecyclerView dayView;

    public static MoneyService moneyService;
    DayAdapter dayAdapter;
    List<MoneyFlow> moneyFlowList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pay_day);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moneyService = retrofit.create(MoneyService.class);

        dayView = findViewById(R.id.dayView);
        dayView.setLayoutManager(new LinearLayoutManager(this));
        dayAdapter = new DayAdapter(this, moneyFlowList);
        dayView.setAdapter(dayAdapter);

        calendarView = findViewById(R.id.calendarView);
        Calendar calendar = Calendar.getInstance();
        int todayYear = calendar.get(Calendar.YEAR);
        int todayMonth = calendar.get(Calendar.MONTH);
        int today = calendar.get(Calendar.DAY_OF_MONTH);

        String todayDate = toFormatDate(todayYear, todayMonth, today);
        fetchData(todayDate);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = toFormatDate(year, month, dayOfMonth);
            fetchData(selectedDate);
        });
        calendarView.setDate(System.currentTimeMillis(), false, true);


        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String date = data.getStringExtra("successfulDate");
                        fetchData(date);
                    } else {
                        Log.d("ActivityResult", "RESULT_OK를 받지 못했습니다.");
                    }

                });
        Button pay_predict=findViewById(R.id.month_pay_rv);
        pay_predict.setOnClickListener(v->{
            Intent intent=new Intent(MainActivity.this, PredictActivity.class);
            intent.putExtra("Date", selectDate);
            launcher.launch(intent);
        });
    }

    public String toFormatDate(int year, int month, int day) {
        String date;
        return date = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, day);
    }

    private void fetchData(String date) {
        selectDate=date;
        Log.v("selectedDate", date); // 넘어옴
        TextView day = findViewById(R.id.day_day);
        day.setVisibility(VISIBLE);
        day.setText(date);

        moneyService.getMoneyFlowDate(date).enqueue(new Callback<List<MoneyFlow>>() {
            @Override
            public void onResponse(Call<List<MoneyFlow>> call, Response<List<MoneyFlow>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    moneyFlowList.clear();
                    moneyFlowList.addAll(response.body());
                    dayAdapter.setDate(date);
                    dayAdapter.notifyDataSetChanged();


                    TextView numberOf = findViewById(R.id.numberOf);
                    numberOf.setVisibility(VISIBLE);
                    String numberText = moneyFlowList.size() + "";
                    numberOf.setText(numberText + " 건");

                    TextView acount = findViewById(R.id.acount);
                    acount.setVisibility(VISIBLE);
                    int acount_num = 0;
                    for (int i = 0; i < moneyFlowList.size(); i++) {
                        if (moneyFlowList.get(i).isSpend() == true) {
                            acount_num += moneyFlowList.get(i).getCost();
                        }
                    }
                    acount.setText(formatting(acount_num));
                }
            }

            @Override
            public void onFailure(Call<List<MoneyFlow>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "no data", Toast.LENGTH_SHORT).show();
            }
        });


    }

}

interface MoneyService {
    @GET("/money/{date}/contents")  //날짜에 따른 일 가계부들을 가져옴
    public Call<List<MoneyFlow>> getMoneyFlowDate(@Path("date") String date);

    @POST("/money/{id}/contents")   //해당 날짜에 일가계부를 하나 생성함
    public Call<MoneyFlow> setMoneyFlow(@Path("id") Long id, @Body MoneyFlow moneyFlow);

    @PATCH("/money/{id}/contents")  //일가계부 중 하나의 데이터를 수정함
    public Call<MoneyFlow> update(@Path("id") Long id, @Body MoneyFlow moneyFlow);

    @DELETE("/money/{id}/contents")  //일가계부 중 하나의 데이터를 삭제함
    public Call<MoneyFlow> delete(@Path("id") Long id);

    @GET("/getMonthlyCost/{year}/{month}")   //해당 월의 예산객체를 가져옴
    public Call<List<Predict>> getMonthlyCost(@Path("year") int year ,@Path("month") int month);

    @GET("/getCategories")     //모든 카테고리의 아이콘 이미지와 카테고리이름을 가져옴
    public Call<List<Categories>> getCategories();

    @POST("/updatePredict")    //예산 객체를 수정함
    public Call<List<Predict>> budgetUpdate(@Body List<Predict> changedBudgetList);

    @GET("/getMonthPay/{categoryId}/{year}/{month}")   //해당 월,카테고리의 일가계부 리스트를 가져옴
    public Call<List<MoneyFlow>> getMonthPay(@Path("categoryId") Long categoryId,@Path("year")int year,@Path("month")int month);
}

class MoneyFlow {


    private Categories category;



    private Long id;
    private Long categoriesId;
    private String nowDate;
    private String content;



    private int cost;


    private boolean spend;

    public MoneyFlow(Long id,Long categoriesId, String nowDate, String content, int cost, boolean spend) {
        this.id=id;
        this.categoriesId = categoriesId;
        this.nowDate = nowDate;
        this.content = content;
        this.cost = cost;
        this.spend = spend;

    }


    public void setCategoriesId(Long categoriesId) {
        this.categoriesId = categoriesId;
    }

    @Override
    public String toString() {
        return "MoneyFlow{" +
                ", id=" + id +
                ", categoriesId=" + categoriesId +
                ", nowDate='" + nowDate + '\'' +
                ", content='" + content + '\'' +
                ", cost=" + cost +
                ", spend=" + spend +
                '}';
    }



    public boolean isSpend() {
        return spend;
    }
    public Long getId() {
        return id;
    }

    public String getNowDate() {
        return nowDate;
    }

    public Categories getCategory() {
        return category;
    }


    public int getCost() {
        return cost;
    }

    public String getContent() {
        return content;
    }

    public Long getCategoriesId() {
        return categoriesId;
    }


}

class Categories {
    private String imageName;
    private int budget;
    private String categoryName;

    private Long id;




    public String getCategoryName() {
        return categoryName;
    }

    public int getBudget() {
        return budget;
    }

    public Long getId() {
        return id;
    }

    public String getImageName() {
        return imageName;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "imageName='" + imageName + '\'' +
                ", budget=" + budget +
                ", categoryName='" + categoryName + '\'' +
                ", id=" + id +
                '}';
    }


}

class Predict  {
    private Long id;
    private int year;
    private int month;
    private Long categoryId;
    private int predict;
    private int monthCost;

    public Predict(Long id, int year, int month,Long categoryId,Categories category, int predict, int monthCost) {
        this.id=id;
        this.year=year;
        this.month=month;
        this.categoryId=categoryId;
        this.predict=predict;
        this.monthCost=monthCost;
    }


    public Long getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public int getPredict() {
        return predict;
    }

    public int getMonthCost() {
        return monthCost;
    }
    public void setPredict(int predict) {
        this.predict = predict;
    }

    @Override
    public String toString() {
        return "Predict{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", categoryId=" + categoryId +
                ", predict=" + predict +
                ", monthCost=" + monthCost +
                '}';
    }
}
