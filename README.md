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

### 목차
  * 1.프로젝트 개요
     * 주요기능 요약
       * CRUD(등록/ 리드/ 수정/ 삭제)
       * 카테고리별 지출관리(6개의 카테고리)
       * 예산대비 지출현황 시각화
       * 월 예산 설정 및 저장
       * 날짜별 지출내역 조회
         * 일별
         * 월별
      * 기술스택
  * 2.프로젝트 설계
     * 엔티티의 관계도
      * 다대일관계
      * 예산의 생성
     * 데이터흐름
     * API 설계
  * 3.기능소개 및 시연
     * 영상
     * 주요 화면 및 동작
       * 날짜선택
       * 예산설정 및 DB반영
       * progressBar를 통한 시각화
  * 4.핵심코드 설명
     * 백엔드
        * 엔티티
        * 일일 가계부 조회 API
     *프론트 엔드
       * 레트로핏 인터페이스
       * 리사이클러뷰 어댑터
  * 5.향후 개선 계획
     * jwt기반 로그인 및 인증기능
     * CI/CD 방법론 적용
     * 예산 초과시 알림기능 추가
   <br><br>
### 1. 프로젝트 개요
> 주요기능 요약
#### CRUD(조회,생성,수정,삭제)
<br>
가계부를 관리하기 위해서 아래와같은 속성의 DB를 CRUD 할 수 있도록 하였습니다.
<br> 
이 프로젝트의 가장 작은 구성요소인 DB입니다. 
<br>
<br>

![일 가계부](https://github.com/user-attachments/assets/cdc7696b-0caf-4a05-b0dc-b6b0d5f551fa)
<br><br><br><br>

#### 카테고리별 지출관리(6개)
<br>
위에 만든 일 가계부를 기반으로 아래와 같이 예산(predict)과 지출(monthCost)을 관리하는 DB가 만들어 지도록 클래스를 구성하였습니다.
<br>
카테고리별로 관리되기 떄문에 카테고리 아이디 속성을 추가하였습니다.
<br>

![예산DB](https://github.com/user-attachments/assets/e3f0c2c8-58f7-47eb-b104-c08cc709a40e)
<br><br><br><br>

#### 예산대비 지출현황 시각화
<br>
안드로이드 스튜디오의 뷰인 프로그레스바를 사용하여 현재 예산대비 지출의 현황을 직관적으로 확인할 수 있게 하였습니다.
<br><br>

![예산뷰](https://github.com/user-attachments/assets/ec4b0fbd-f010-4f1e-9f10-c9a524bb5b80)

<br><br><br><br>
#### 월 예산 설정 및 저장
<br>
아래 사진과 같이 사용자가 월별, 카테고리별 예산을 직접 설정하고 관리할 수 있게 하였습니다.
<br>
<br>

![예산설정](https://github.com/user-attachments/assets/90a10031-75df-4a3a-934d-c6616745e7bc)
<br>

<br><br><br><br>
#### 날짜별 지출내역 조회
<br>

## 📅 날짜 기반 가계부 조회 기능

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
> 기술 스택

<br> 프런트 엔드는 안드로이드 스튜디오를 사용하였습니다. 프런트엔드 단의 레트로핏과 서버의 스프링부트를 연결해 JSON데이터를 주고 받는 RESTAPI를 사용했습니다.
<br> 그림의 윗부분처럼 일단 처음엔 윈도우 운영체제에서 테스트 DB인 h2를 연결해 사용하였습니다.
<br>코딩을 마무리 한 후엔 AWS의 서버인 EC2의 리눅스 운영체제에 자바개발도구를 설치하고 개발한 코드들과 초기설정값들을 jar파일로 복사한걸 EC2에서 실행되게 하였고
<br> RDS의 엔진으로는 postgresql을 DB로 사용하므로써 유지보수, 백업, 업데이트 등 운영부담을 감소시켰습니다.
![image](https://github.com/user-attachments/assets/e0976b31-5ba4-4561-9b53-77a741d49bc7)

<br><br><br>
인증 : JWT (도입 예정)
<br>
CI/CD (도입 예정)
<br><br><br>
### 2. 프로젝트 설계
<br>

> 엔티티의 관계도

<br><br>
#### 다대일관계
<br>
일가계부, 카테고리, 예산 총 3가지의 엔티티가 있습니다. 이 프로젝트는 아래 그림과 같이 일가계부, 예산이 다대 카테고리 는 일로 다대일관계가 맺어져 있습니다.
<br>

![다대일 관계도](https://github.com/user-attachments/assets/bbc91be6-bed0-4713-95f1-62754ae37d57)

<br> 다대일 관계를 사용하는 이유는 아이디값만 받아도 그 아이디값으로 해당하는 참조자를 연결해 주는것입니다. 그래서 복잡한 쿼리문을 사용할 필요없이 DB의 새로운 흐름을 만들 수 있습니다.
하지만 이 프로젝트의 경우 관계형 DB의 장점을 정확히 사용하지 못했습니다.일가계부를 기반으로 예산을 만드는거라 사실 이 두 엔티티간에 다대일 관계를 맺었어야 합니다. <br>
연관성이 있는게 날짜인데 데이터 타입이 달라 그 방법을 찾지못한 점이 아쉽습니다.

<br>

> 예산의 객체생성


<br> 아래 화면이 나타날때 예산객체가 만들어질지 결정되도록 했습니다.

![예산뷰](https://github.com/user-attachments/assets/e991337b-ca1f-4f4b-b372-437edf8b29c0)

<br> 아래 코드는 백엔드의 컨트롤러에서 호출한, 작업만을 수행하는 매써드를 따로 만든것입니다. <br>백엔드에서 년, 월을 받아서 일가계부의 리파지터리에 있는 쿼리문 함수를 참고해서 예산리스트에
추가하거나 원래 객체를 수정해서 DB에 다시저장합니다.


```java
public List<PredictDTO> monthlySetUp(int year, int month) {
        List<Object[]> results = moneyFlowRepo.getMonthlyCostByCategory(year, month);
```

<br> 매써드 안에 생략된 구문은 아래 알고리즘에따라 DB를 저장하도록 만들었습니다.

<br>

![예산알고리즘](https://github.com/user-attachments/assets/e0a1b000-5a3f-468c-9fd2-724a73b315eb)

<br><br>
> 데이터흐름
<br>
①사용자가 메인에서 특정 날짜를 클릭 > 날짜에 해당하는 일 가계부들이 리사이클러뷰로 보여짐(추가,생성,수정,삭제가능) <br>
②메인에서 <이번달 예산> 버튼을 클릭하면 예산액티비티로 넘어가면서 서버에서 해당월의 예산 리스트를 가져와서 프로그레스바에 표시 <br>
③예산 액티비티에서<설정> 버튼을 클릭하면 예산객체들의 예산 속성을 변경할 수 있는 예산설정 액티비티에서 사용자 입력을 기다림<br>
④예산설정 액티비티에서 <설정> 버튼을 누르면 입력한 예산으로 리스트의 객체들을 수정한걸 서버로 보내 DB에 저장함
<br><br><br><br>

> API설계
<br>
프런트 엔드에서 요청하는 HTTP메써드, URL들과 간략한 명세입니다. @Path는 URL에 포함되어 전달되고, @Body는 json데이터로 http요청의 본문에 참조자나 리스트가 전달되어 서버에서 dto로 받게됩니다.
<br> 메써드의 리턴타입으로 서버에서 통신후 받아오는 데이터 타입을 확인할 수 있습니다.
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

    @GET("/getCategories")     //모든 카테고리의 아이콘 이미지와 카테고리이름을 가져옴
    public Call<List<Categories>> getCategories();

    @POST("/updatePredict")    //예산 객체를 수정함
    public Call<List<Predict>> budgetUpdate(@Body List<Predict> changedBudgetList);

    @GET("/getMonthPay/{categoryId}/{year}/{month}")   //해당 월,카테고리의 일가계부 리스트를 가져옴 
    public Call<List<MoneyFlow>> getMonthPay(@Path("categoryId") Long categoryId,@Path("year")int year,@Path("month")int month);
}
```

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

#### 일일 가계부 조회 API
<br>
아래 그림의 주석에서 보이는것처럼 백엔드가 정상 구동되는지 탈렌드 사이트를 이용하거나 직접 url에서 테스트 하는 과정이 있었습니다. <br>
JPA 라이브러리인 리파지터리를 이용하니 속성값에 따라 자동 조회해주는, 기본적으로 제공되는 함수(findByNowDate등)를 이용할 수 있어 편했습니다. 
<br>

```java
//http://localhost:8080/money/2025-02-26/contents 되는것 확인
    @GetMapping("/money/{date}/contents")
    public List<MoneyFlow> getByDate(@PathVariable("date") String date) {
        System.out.println(date);
        LocalDate localDate = LocalDate.parse(date);
        return moneyFlowRepo.findByNowDate(localDate);
    }
```

<br><br><br><br>

> 프론트엔드 주요코드

#### Retrofit API 인터페이스

<br> 아래와같이 동일한 HTTP메써드와 URL을 가진 프론트엔드 인터페이스의 메써드를 호출해주면 레트로핏을 붙인 객체가 통신을 시도합니다.

```java
@GET("/money/{date}/contents")  //날짜에 따른 일 가계부들을 가져옴
    public Call<List<MoneyFlow>> getMoneyFlowDate(@Path("date") String date);
```

<br> 레트로핏은 아래와 같이 서버가 구축되어있는 포트에 연결하여 인터페이스를 사용하도록 객체를 만들었습니다. <br>모든 API통신은 이것을 통해 되도록 했습니다.

```java
Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moneyService = retrofit.create(MoneyService.class);
```

<br><br><br><br>
#### RecyclerView 어댑터
<br>
아래 코드는 날짜를 클릭으로 바꿀때마다 실행되는 함수안에서 date라는 변수가 바뀜으로써 비동기적인 통신을 하는것입니다.
<br>리사이클러뷰 어댑터를 셋팅할때 생성자로 보내놓았던 일가계부 리스트를 notifyDataSetChanged()로 새로고침 하도록 하였습니다.
<br>

```java
moneyService.getMoneyFlowDate(date).enqueue(new Callback<List<MoneyFlow>>() {
            @Override
            public void onResponse(Call<List<MoneyFlow>> call, Response<List<MoneyFlow>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    moneyFlowList.clear();
                    moneyFlowList.addAll(response.body());
                    dayAdapter.setDate(date);
                    dayAdapter.notifyDataSetChanged();
```


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
