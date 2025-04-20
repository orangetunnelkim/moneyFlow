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
   3.2 예산 설정 및 시각화 (ProgressBar + 예산 저장)  
   3.3 카테고리별 지출 관리 (다대일 매핑 + 이미지, 이름 출력)  

4. 📸 기능 시연  
   4.1 실행 영상  
   4.2 주요 화면 스크린샷  
   4.3 사용자 흐름 요약

5. 💻 핵심 코드 스니펫  
   5.1 Backend (Spring Boot)  
       - 엔티티 설계  
       - 날짜별 조회 API  
   5.2 Frontend (Android)  
       - Retrofit 인터페이스  
       - RecyclerView 어댑터  
       - 데이터 바인딩 및 UI 갱신

6. 🚀 향후 발전 방향  
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
![흐름도](https://github.com/user-attachments/assets/15e48d00-df95-4508-b004-f4449e829629)

<br>
①사용자가 메인에서 특정 날짜를 클릭 > 날짜에 해당하는 일 가계부들이 리사이클러뷰로 보여짐(생성,수정,삭제가능) <br>
②메인에서 <이번달 예산> 버튼을 클릭하면 예산액티비티로 넘어가면서 서버에서 해당월의 예산 리스트를 가져와서 프로그레스바에 표시 <br>
③예산 액티비티에서<설정> 버튼을 클릭하면 예산객체들의 예산 속성을 변경할 수 있는 예산설정 액티비티에서 사용자 입력을 기다림<br>
④예산설정 액티비티에서 <설정> 버튼을 누르면 입력한 예산으로 리스트의 객체들을 수정한걸 서버로 보내 DB에 저장함
<br><br><br><br>

#### 2.2 API명세 및 구조
<br>
프런트 엔드에서 요청하는 HTTP메써드, URL들과 간략한 명세입니다. @Path는 URL에 포함되어 전달되고, @Body는 json데이터로 http요청의 본문에 참조자나 리스트가 전달되어 서버에서 dto 객체로 매핑됩니다.
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

<br><br><br>
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
