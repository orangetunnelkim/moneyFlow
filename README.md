# 일정, 가계부 프로그램
<br>

>스프링부트 프레임워크와 안드로이드 스튜디오를 Retrofit으로 연결해 RESTFULAPI를 사용하는 프로젝트입니다.

### 프로젝트 기간 2025.02.25 ~ 2025.04.01 (총 5주)
### 팀원 김지욱(본인), [백건우](https://github.com/gunwoo100/project_schedule), [엄정현](https://github.com/natanal-hyun/sometodo-app)
<br>

### 시간배분
주차|주요 작업|상세 내용
---|---|---|
1~3주차|개인별 프로젝트|개인별 AndroidUI+SpringBoot API
4주차|프로젝트 연결|팀별 AndroidUI+SpringBoot API
5주차|통합 테스트 및 보완|피드백 및 보고서 작성
<br>
<br>

### 역할 및 기능계획
> 팀원들이 각각 풀스택 개발을 담당하여 자신이 맡은 기능을 구현하였습니다.
<br>

1. **엄정현** : 회원관리 담당
<br> 회원가입, 로그인, 회원 정보 관리
<br>

2. **백건우** : 일정 담당
<br> 일정, 할 일(toDo리스트) 관리
<br>

3. **김지욱** : 가계부 담당
<br> 가계부, 예산관리
<br>

이 소개글은 **가계부 프로그램**에 대한 내용입니다.

<br>
<br>

  ### 📖 목차

1. 🔍 프로젝트 개요  
   1.1 주요 기능 요약  
   1.2 핵심 기술 스택

2. 🧠 설계 구조 및 기술 흐름  
   2.1 데이터 모델 및 엔티티 관계  
   2.2 주요 기능 흐름  
   2.3 API 명세 및 구조


3. 💡 핵심 구현 포인트 (중요 기능 상세 설명)  
   3.1 날짜별 지출 내역 조회 (RecyclerView + Retrofit + CalendarView)  
   3.2 프론트엔드 - 리사이클러뷰 어댑터 구현  
   3.3 월별 예산(Predict) 객체 자동 생성 및 관리 로직  

5. 📸 기능 시연  
   4.1 실행 영상  
   4.2 주요 화면 스크린샷  
   4.3 사용자 흐름 요약

6. 💻 핵심 코드 스니펫  
   5.1 Backend (Spring Boot)  
       - 엔티티 설계  
       - 날짜별 조회 API  
   5.2 Frontend (Android)  
       - Retrofit 인터페이스  
       - RecyclerView 어댑터  
       - 데이터 바인딩 및 UI 갱신

7. 🚀 향후 발전 방향  
   - JWT 기반 로그인 인증  
   - 예산 초과 시 알림 기능  
   - AWS RDS + CI/CD 구축
   <br><br>
### 1. 🔍 프로젝트 개요
#### 1.1 주요기능 요약
> CRUD(조회,생성,수정,삭제)

<br> 
날짜, 수입/지출여부, 카테고리아이디, 사용처, 지출금액 속성을 가진 DB를 CRUD 할 수 있도록 하였습니다.
<br>
<br>

![일가계부 간단](https://github.com/user-attachments/assets/eaa14e92-23c1-4291-81ad-eb91bc9f74b0)


<br><br><br><br>

> 카테고리별 지출,예산 관리(6개)
<br>
위에 만든 일 가계부를 기반으로 아래와 같이 예산(predict)과 지출(monthCost)을 관리하는 DB가 만들어 지도록 클래스를 구성하였습니다.
<br>
해당년월, 예산, 월 지출, 카테고리 아이디 속성이 있습니다.
<br>

![예산DB](https://github.com/user-attachments/assets/e3f0c2c8-58f7-47eb-b104-c08cc709a40e)
<br><br><br><br>

> 예산대비 지출현황 시각화
<br>
안드로이드 스튜디오의 뷰인 프로그레스바를 사용하여 현재 예산대비 지출의 현황을 직관적으로 확인할 수 있게 하였습니다.
<br><br>

![예산뷰](https://github.com/user-attachments/assets/ec4b0fbd-f010-4f1e-9f10-c9a524bb5b80)

<br><br><br><br>
> 월 예산 설정 및 저장
<br>
아래 사진과 같이 사용자가 월별, 카테고리별 예산을 직접 설정하고 관리할 수 있게 하였습니다.
<br>
<br>

![예산설정](https://github.com/user-attachments/assets/90a10031-75df-4a3a-934d-c6616745e7bc)
<br>

<br><br><br><br>



#### 1.2 기술 스택

## 🛠 기술 스택 및 인프라 구성

### 📱 Frontend (Android Studio)
- Java 기반 Android 앱 개발
- `Retrofit2` 사용하여 백엔드(Spring Boot)와 REST API 통신
- JSON 포맷으로 데이터 송수신
- 날짜 선택, 예산 설정, 지출 시각화 등 UI 기능 구현

### 🌐 Backend (Spring Boot)
- `Spring Boot` 기반 RESTful API 서버 구축
- 테스트 환경: `H2` 인메모리 데이터베이스 사용
- 빌드 후 `.jar` 파일로 패키징하여 배포

### ☁️ 인프라 (AWS)
- **서버**: `EC2` 인스턴스 (리눅스 기반)  
  → JDK 설치 후 `.jar` 파일 실행을 통해 백엔드 서버 운영
- **DB**: `AWS RDS - PostgreSQL`  
  → 데이터 영속성 확보 및 백업, 유지보수, 확장성 강화
- 배포 이후에도 유지보수 및 기능 업데이트가 용이하도록 구성



![image](https://github.com/user-attachments/assets/e0976b31-5ba4-4561-9b53-77a741d49bc7)

<br><br><br>
인증 : JWT (도입 예정)
<br>
CI/CD (도입 예정)
<br><br><br><br><br><br>
### 2. 🧠 설계 구조 및 기술 흐름  
<br>

####    2.1 데이터 모델 및 엔티티 관계

<br><br>
> 다대일관계
<br>
일가계부, 카테고리, 예산 총 3가지의 엔티티가 있습니다. 이 프로젝트는 아래 그림과 같이 일가계부, 예산이 다대 카테고리 는 1로 각각 다대일관계가 맺어져 있습니다.
<br>

![다대일 관계도](https://github.com/user-attachments/assets/bbc91be6-bed0-4713-95f1-62754ae37d57)

<br> 이번 프로젝트에서는 카테고리와 일일 가계부 간의 관계를 @ManyToOne으로 설정해, 카테고리 ID만으로 연관된 정보를 쉽게 조회할 수 있도록 구현했습니다. 이를 통해 복잡한 쿼리 없이도 효율적인 데이터 관리를 할 수 있었습니다.

다만, 예산(Budget)과 일일 가계부(MoneyFlow) 사이에는 실제 로직상 연관성이 깊음에도 불구하고 관계 매핑을 하지 못한 점은 아쉬웠습니다. 두 엔티티의 공통 연결점인 LocalDate의 타입 차이로 인해 JPA 매핑에 제약이 있었기 때문입니다.

향후에는 @JoinColumn, 복합키, 또는 중간 테이블 설계 등을 통해 더 명확한 엔티티 간 관계 설정을 적용할 예정이며, 이번 경험을 통해 JPA의 관계 설계에 대한 이해도를 높일 수 있었습니다.

<br>

#### 2.2 주요기능 흐름
<br><br>
![흐름도](https://github.com/user-attachments/assets/8354b30e-c114-4703-8e0f-56232e817758)



<br>
①사용자가 메인에서 특정 날짜를 클릭 > 날짜에 해당하는 일 가계부들이 리사이클러뷰로 보여짐(생성,수정,삭제가능) <br>
②메인에서 <이번달 예산> 버튼을 클릭하면 예산액티비티로 넘어가면서 서버에서 해당월의 예산 리스트를 가져와서 프로그레스바에 표시 <br>
③예산 액티비티에서<설정> 버튼을 클릭하면 예산객체들의 예산 속성을 변경할 수 있는 예산설정 액티비티에서 사용자 입력을 기다림<br>
예산설정 액티비티에서 <설정> 버튼을 누르면 입력한 예산으로 리스트의 객체들을 수정한걸 서버로 보내 DB에 저장함<br>
④예산 액티비티에서 금액을 누르면 클릭된 카테고리로 사용한 그 달의 일 가계부 리스트들이 불러와짐
<br><br><br><br>

#### 2.3 API명세 및 구조
<br>
프런트 엔드에서 요청하는 HTTP메써드, URL들과 간략한 명세입니다.
<br> 메써드의 리턴타입으로 서버에서 통신후 받아오는 데이터 타입을 정의 했습니다.
<br>

```java
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

    @GET("/getCategories")     //모든 카테고리의 아이콘 이미지와 카테고리이름을 가져옴 (리사이클러뷰 표시용)
    public Call<List<Categories>> getCategories();

    @POST("/updatePredict")    //예산 객체를 수정함
    public Call<List<Predict>> budgetUpdate(@Body List<Predict> changedBudgetList);

    @GET("/getMonthPay/{categoryId}/{year}/{month}")   //해당 월,카테고리의 일가계부 리스트를 가져옴 
    public Call<List<MoneyFlow>> getMonthPay(@Path("categoryId") Long categoryId,@Path("year")int year,@Path("month")int month);
}
```

<br><br><br><br>
### 3. 💡 핵심 구현 포인트 (중요 기능 상세 설명)  

#### 3.1 📅 날짜 기반 가계부 조회 기능

사용자가 `CalendarView`에서 날짜를 선택하면, 해당 날짜의 지출 내역을 서버에서 받아와 RecyclerView에 표시합니다.  
클라이언트와 서버 간 비동기 통신을 통해 날짜별 데이터를 효율적으로 처리하며, 사용자에게 즉각적인 피드백을 제공합니다.

### ✅ 흐름 요약

1. 사용자 캘린더 날짜 선택
2. 선택된 날짜를 문자열로 변환
3. 서버에 해당 날짜의 데이터를 요청 (Retrofit)
4. 응답받은 리스트를 RecyclerView에 연결하여 화면 갱신

---

### 🖱️ 1. 날짜 선택 이벤트 감지

```java
calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
    String selectedDate = toFormatDate(year, month, dayOfMonth);
    fetchData(selectedDate); // 서버 요청 함수 호출
});
```

사용자가 날짜를 누르면 fetchData() 함수 실행

선택한 날짜는 yyyy-MM-dd 형식의 문자열로 변환
<br><br><br>
### 🔄 2. 서버 요청 및 RecyclerView 갱신

```java
private void fetchData(String date) {
    moneyService.getMoneyFlowDate(date).enqueue(new Callback<List<MoneyFlow>>() {
        @Override
        public void onResponse(Call<List<MoneyFlow>> call, Response<List<MoneyFlow>> response) {
            if (response.isSuccessful() && response.body() != null) {
                moneyFlowList.clear();
                moneyFlowList.addAll(response.body());
                dayAdapter.setDate(date);
                dayAdapter.notifyDataSetChanged(); // UI 갱신
            }
        }

        @Override
        public void onFailure(Call<List<MoneyFlow>> call, Throwable t) {
            // 예외 처리
        }
    });
}
```

Retrofit을 사용하여 서버와 비동기 통신

성공적으로 데이터를 받아오면 리스트 갱신 및 UI 업데이트
<br><br><br><br>
### 🔗3. Retrofit 연결 설정

```java
Retrofit retrofit = new Retrofit.Builder()
    .baseUrl("http://10.0.2.2:8080")
    .addConverterFactory(GsonConverterFactory.create())
    .build();

moneyService = retrofit.create(MoneyService.class);
```



### 🧩 4. 인터페이스 정의
```java
interface MoneyService {
    @GET("/money/{date}/contents")
    Call<List<MoneyFlow>> getMoneyFlowDate(@Path("date") String date);
}
```

### 🌐 5. 서버 - Spring Boot Controller
<br>

```java
@GetMapping("/money/{date}/contents")
public List<MoneyFlow> getByDate(@PathVariable("date") String date) {
    LocalDate localDate = LocalDate.parse(date);
    return moneyFlowRepo.findByNowDate(localDate);
}
```

클라이언트로부터 날짜를 받아 LocalDate로 변환

해당 날짜의 MoneyFlow 데이터를 DB에서 조회하여 반환



<br><br><br>
### 💡 기술 포인트
CalendarView의 setOnDateChangeListener 활용

Retrofit 기반 비동기 API 연동

Spring Boot REST API 구성 (/money/{date}/contents)

RecyclerView 리스트 업데이트 구조화

클라이언트-서버 간 날짜 포맷 통일 (yyyy-MM-dd)



<br><br><br><br>

#### 3.2🔄 RecyclerView 클릭에 따른 동적 처리 (수정 vs 추가)
<br>

### 🔘 프론트엔드- RecyclerView 어댑터 구현

RecyclerView의 마지막 항목에 `"내역 추가"` 버튼 형태의 뷰를 함께 표시하여,  
하나의 어댑터 내에서 **"기존 데이터 리스트" + "새 항목 추가 인터페이스"** 를 함께 구현했습니다.

#### 📌 핵심 설계 의도
- 사용자가 목록의 마지막에서 바로 새로운 지출 내역을 추가할 수 있도록 직관적인 UI 제공
- 어댑터의 **데이터 리스트 개수 +1** 만큼 ViewHolder를 만들어 분기 처리
- 동일한 ViewHolder에서 `onClick()` 이벤트를 분기하여, 클릭 위치에 따라 `AddPay`, `EditPay` 두 액티비티로 이동

#### ✅ 구현 개요

##### 1. View 개수 설정
```java
@Override
public int getItemCount() {
    return moneyFlowList.size() + 1; // 리스트 + 추가 버튼
}
```

##### 2. 항목 바인딩시 분기처리
```java
@Override
public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    if(position < moneyFlowList.size()) {
        MoneyFlow moneyFlow = moneyFlowList.get(position);
        Categories category = moneyFlow.getCategory();
        holder.setItemView(moneyFlow, category); // 기존 항목 표시
    } else {
        holder.setFinalView(); // 마지막 항목은 '추가' 버튼
    }
}
```

##### 3. 뷰 바인딩 함수
```java
public void setItemView(MoneyFlow moneyFlow, Categories category) {
    int imageId = getResourceId(category.getImageName());
    dayCategoryImage.setImageResource(imageId);
    dayContent.setText(moneyFlow.getContent());
    dayCategoryName.setText(category.getCategoryName());
    dayPrice.setText(MainActivity.formatting(moneyFlow.getCost()));
}

public void setFinalView() {
    dayCategoryImage.setImageResource(R.drawable.add);
    dayContent.setVisibility(INVISIBLE);
    dayCategoryName.setVisibility(INVISIBLE);
    dayPrice.setText("내역 추가");
}
```

##### 4. 클릭 이벤트에 따른 분기
```java
@Override
public void onClick(View view) {
    int position = getAdapterPosition();

    if (position == moneyFlowList.size()) {
        // '내역 추가' 클릭 시
        Intent intent = new Intent(mContext, AddPay.class);
        intent.putExtra("Date", date);
    } else {
        // 기존 항목 클릭 시
        Intent intent = new Intent(mContext, EditPay.class);
        intent.putExtra("position", position);
    }

    ((MainActivity) mContext).getLauncher().launch(intent);
}
```
<br>

#### ✅ 주요 구현 포인트 요약

- **하나의 RecyclerView 어댑터로 기존 항목 + 추가 항목 처리**  
  → `getItemCount()`에서 리스트 사이즈 +1, 마지막 포지션에 "내역 추가" 뷰 배치  

- **`onBindViewHolder()`에서 포지션 기준으로 두 가지 뷰 분기**  
  → 일반 내역은 `setItemView()`, 추가 항목은 `setFinalView()` 호출  

- **클릭 시 position에 따라 다른 액티비티로 분기 이동**  
  → 기존 내역 클릭 → `EditPay`, 마지막 항목 클릭 → `AddPay`

- **카테고리 ID만으로 전체 참조 객체까지 연결해 UI 표시**  
  → 이미지, 이름 등 Category 정보를 직접 렌더링  

- **유지보수에 유리한 구조로 기능 분기 및 UI 구성**

<br><br><br><br><br><br>

#### 3.3 🔄 월별 예산(Predict) 객체 자동 생성 및 관리 로직
<br><br><br><br>
#### 🧭 전체 흐름
- 사용자가 메인 화면에서 버튼을 클릭
→ selectDate (예: "2025-04-01")를 Intent로 전달

- PredictActivity에서 전달받은 날짜 분해
→ selectYear, selectMonth를 추출하여 백엔드 요청

- Retrofit을 통해 월별 예산/지출 데이터 요청
→ GET /getMonthlyCost/{year}/{month}

- 백엔드는 해당 월의 지출 데이터를 카테고리별로 집계 + 예산 데이터와 매칭
→ 없는 예산은 monthCost만 채워서 추가

- 프론트에서는 총합 지출과 예산을 계산하여 UI에 시각화
→ ProgressBar, TextView, RecyclerView를 활용하여 구성
  <br><br><br><br>
#### 💡 설계 의도
- "이번 달 예산" 버튼 클릭 시, 사용자가 선택한 날짜 기준으로
- 해당 월의 모든 카테고리에 대한 예산(Predict) 정보를 UI에 표시

- 이때 해당 카테고리에 지출 내역이 없어도
➤ 지출: 0, 예산: 0 상태의 Predict 객체가 자동으로 생성되어야 함
➤ 즉, UI 기준으로 모든 카테고리에 Predict 객체가 존재해야 함

<br><br><br>
📱 프론트엔드 처리 흐름
```java
Button pay_predict = findViewById(R.id.month_pay_rv);
pay_predict.setOnClickListener(v -> {
    Intent intent = new Intent(MainActivity.this, PredictActivity.class);
    intent.putExtra("Date", selectDate); // ex. "2025-04-01"
    startActivity(intent);
});
```
```java
// PredictActivity onCreate() 내부 구문
String[] parts = intent.getStringExtra("Date").split("-");
selectYear = Integer.parseInt(parts[0]);
selectMonth = Integer.parseInt(parts[1]);

showMonthCost(selectYear, selectMonth)); //showMonthCost()함수 내부에서 getMonthlyCost() 서버 통신 메써드호출
```
<br><br>
선택된 날짜를 기준으로 월별 예산 데이터를 백엔드에 요청
<br><br>

🔧 백엔드 요청 및 처리
```java
@GET("/getMonthlyCost/{year}/{month}") //Retrofit 요청 접수부분
Call<List<Predict>> getMonthlyCost(@Path("year") int year ,@Path("month") int month);
```
```java
@GetMapping("/getMonthlyCost/{year}/{month}")
public ResponseEntity<List<PredictDTO>> setupMonthlyCost(...) {
    List<PredictDTO> predictDTOList = monthlySetUp(year, month);
    return ResponseEntity.ok(predictDTOList);
}
```
<br><br><br>
⚙️ 현재 구현 방식 (백엔드)
```java
List<Object[]> results = moneyFlowRepo.getMonthlyCostByCategory(year, month); //일 가계부 데이터를 기반으로 한 복합쿼리문으로 데이터흐름 만듬.
// 결과: 해당 월에 지출 내역이 있는 카테고리만 포함

List<Predict> existingPredicts = predictRepo.findByYearAndMonth(year, month);
List<Predict> spendingList = new ArrayList<>(existingPredicts);

results.forEach(row -> {
    // 카테고리 참조자, 총지출 파싱
if (existingPredict != null) {
    existingPredict.setMonthCost(totalCost);  // 지출 금액만 업데이트
} else {
    spendingList.add(new Predict(null, year_p, month_p, category, 0, totalCost));  // 예산은 0으로 기본 생성
}
 // 있으면 업데이트, 없으면 새로 생성
});
```
**DB생성 로직**
<br><br>
| 지출 데이터 | 예산 데이터 | 처리 방식                                                         |
|-------------|--------------|------------------------------------------------------------------|
| 있음        | 있음         | `monthCost`만 업데이트                                          |
| 있음        | 없음         | `Predict` 새로 생성 (예산 0, 지출만 있음)                      |
| 없음        | 있음         | 그대로 둠 (이번 달 해당 카테고리 지출 없음)                    |
| 없음        | 없음         | `Predict` 생성되지 않음                                        |

<br><br><br>

**조건에 따른 DB설계**
- `@Query`를 사용한 복합 SQL 쿼리로 월별 카테고리 지출 집계 처리
- 예산 데이터가 있는 경우에는 지출만 업데이트, 없는 경우는 Predict 객체 새로 생성
- 결과적으로 Predict 테이블은 월별-카테고리별로 지출 정보가 있는 경우에만 자동 갱신되며, 예산이 설정되지 않은 경우에도 뷰에서 시각적으로 표시 가능
- 이는 DB를 직접 조작하는 것이 아니라, 데이터 흐름에 따라 동적으로 객체를 생성/갱신하는 방식으로 효율적 데이터 관리 구현

<br>

- ✅ 장점: 실제 지출 내역에 따라 Predict를 구성함
- ❌ 단점: 지출 내역이 전혀 없는 카테고리는 누락됨 → 예산을 설정하는 작업시 null처리를 따로 해줘야함


<br><br><br>

🚨 개선 포인트 (설계 의도와 불일치)

- List<Object[]> results = ... 쿼리는 money_flow 테이블 기준으로 구성됨

➤ 지출이 없는 카테고리는 결과에서 누락되므로 Predict 객체도 생성되지 않음

- 이는 해당 월, 카테고리에 지출이 없어도 예산객체를 생성하려 했던 초기기획과 어긋남

<br><br><br>
✅ 개선 아이디어 (LEFT JOIN 활용)

```java
SELECT 
    :year AS year,
    :month AS month,
    c.id AS category_id,
    SUM(m.cost) AS total_cost // 이부분을 COALESCE(SUM(m.cost), 0) AS total_cost 로 수정 
FROM 
    categories c
LEFT JOIN 
    money_flow m 
ON 
    c.id = m.categories_id 
    AND YEAR(m.now_date) = :year 
    AND MONTH(m.now_date) = :month 
    AND m.spend = true
GROUP BY 
    c.id

```

- categories를 기준으로 LEFT JOIN → 모든 카테고리를 기준으로 Predict 생성 가능








### 3. 기능소개 및 시연
<br>

> 실행 영상
<br>

👉 [실행영상 보러가기](https://youtube.com/shorts/RXqdzRODD6k?si=Yqs7NZlkJiOhvtNX)
ctrl키를 누른채로 클릭하면 새창이 열립니다.

<br><br><br><br>

>주요 화면 및 동작


#### 날짜선택
<br>
아래 그림처럼 메인화면에서 달력의 한 날짜를 클릭하면 그 날짜의 일가계부들이 아래 리사이클러뷰로 표시됩니다.
<br> 내역추가 뷰를 클릭하면 데이터를 생성하는 액티비티가 나오고, 일가계부를 클릭하면 수정이나 삭제하는 액티비티가 나오도록 하였습니다.
<br><br>


![날짜선택](https://github.com/user-attachments/assets/b10bc31c-6e03-4685-9cfb-e55e0d700cef)
<br><br><br><br>



#### 예산설정 및 DB반영
<br>
메인화면에서 <이번 달 예산>을 클릭하면 아래 그림처럼 열리는 예산액티비티에서 예산테이블을 이용해 뷰를 셋팅 해야 했습니다. 예산객체생성 알고리즘에 따라 예산을 0원으로 하는 객체를 생성하거나, 원래 있다면 지출만 새로쓰는 DB 변환과정을 한번 거치도록 하였습니다.
<br> <설정>버튼을 누르면 예산을 직접 수정하는 액티비티(오른쪽그림)가 열리고, <설정>을 누르면 입력한 예산으로 서버와 통신을 하여 예산리스트의 DB들을 수정하도록 하였습니다.
 
![예산DB설정](https://github.com/user-attachments/assets/f45e9160-b28f-4cc8-82e8-4329bb74f424)


<br><br><br><br>

#### progressBar를 통한 시각화
<br>

```java
                int monthAmount=p.getMonthCost();
                int mothBudget=p.getPredict();
                int percentage=(int) ((monthAmount/(float)mothBudget)*100);
                budgetRatio.setProgress(percentage);
``` 

<br>
이러한 방법으로 프런트엔드에서는 서버로부터 가져온 객체를 이용해 프로그레스바로 쉽게 표현했습니다.
<br><br><br><br>

### 4.핵심코드 설명

>백엔드 주요코드

#### 엔티티

```java
@Entity   // 자바의 객체를 DB가 이해할 수 있는 언어로 변환해줌
@Getter  
@AllArgsConstructor //생성자 자동생성
@NoArgsConstructor
@Builder    //생성자 만들때 위치기반이 아닌 속성이름으로 매칭시킬 수 있음 
@ToString       
@Setter
public class MoneyFlow {
    @Column(name="now_date")
    private LocalDate nowDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne  // 다대일 관계
    @JoinColumn(name = "categories_id") //카테고리 아이디를 객체생성할때 넣으면 카테고리에 참조자와 연결됨
    private Categories category;
    @Column
    private String content;
    @Column
    private int cost;
    @Column
    private boolean spend;
```
<br><br><br>


<br><br><br><br>
백엔드에서 새로 가져온 리스트로 리사이클러뷰 어댑터에서는 아래와 같이 리스트중 참조자 하나씩을 돌면서 리사이클러뷰에 뷰를 붙이는 작업을 하게됩니다.
<br>
```java
 public void setItemView(MoneyFlow moneyFlow,Categories category){
            int imageId= this.itemView.getContext().getResources().getIdentifier(
                    category.getImageName(),"drawable",this.itemView.getContext().getPackageName());
            dayContent.setVisibility(VISIBLE);
            dayCategoryName.setVisibility(VISIBLE);
            dayCategoryImage.setImageResource(imageId);
            dayContent.setText(moneyFlow.getContent());
            dayCategoryName.setText(category.getCategoryName());
            dayPrice.setText(MainActivity.formatting(moneyFlow.getCost()));
        }
```
<br><br><br><br>



### 5. 향후 개선 계획

> jwt기반 로그인 및 인증 기능 추가

팀프로젝트이기 때문에 회원관리를 담당한 팀원이 jwt를 도입해 서버와 클라이언트들을 연결했지만,
<br>프로젝트 제작 시간관계상 배포가 되지 않아 일정, 가계부를 담당한 팀원들은 jwt에대한 학습과 구현을 하지 못했습니다.
<br><br>

> CI/CD 적용

자동 빌드, 테스트, 코드 품질검사<br>
지속적 배포, 전달을 통해 개발속도 향상과 지속적인 개선을 가능하게 하는 효과를 기대할 수 있습니다.

<br><br><br><br>
이상 가계부 프로젝트에 대한 소개를 마칩니다. 감사합니다.
