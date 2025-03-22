# 일정, 가계부 프로그램
<br>

>스프링부트 프레임워크와 안드로이드 스튜디오를 Retrofit으로 연결해 서버 API와 통신하는 프로그램입니다.

### 프로젝트 기간 2025.02.25 ~ 2025.04.01 (총 5주)
### 팀원 김지욱(본인), 백건우, 엄정현
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
* 실행 영상
* 가계부 관리 설계
  * 일일 가계부(일 기준)
    * CRUD(등록/ 리드/ 수정/ 삭제)
  * 예산 (월 기준)
    * 예산 표시
    * 예산 등록
  * 기대 효과
 * 객체지향적 데이터관리
   * 역할과 포함된 속성
     * MoneyFlow 클래스
     * Categories 클래스
     * Predict 클래스
 * RestAPI
    * 스프링부트
    * 레트로핏
    * 사용방법
   

<br>
<br>

### 1. 실행 영상
[실행영상 보러가기](https://youtube.com/shorts/RXqdzRODD6k?si=Yqs7NZlkJiOhvtNX)
ctrl키를 누른채로 클릭하면 새창이 열립니다.

### 2. 가계부 관리 설계 <br><br>

>일일 가계부 관리 <br>

하루 하루 사용한 금액을 사용처(카테고리)별로 저장, 조회, 수정, 삭제하는 기능을 만듭니다. <br>
날짜별 이동은 캘린더를 통해 직관적인 UI를 제공합니다.<br><br>


>예산 관리(월별) <br>

일 가계부 등록으로 만들어진 db들을 기반으로 월별,카테고리별로 사용한 금액을 조회할 수 있습니다.<br>
월별, 카테고리별로 목표 예산을 설정하고, 예산대비 이번달 사용금액의 비중을 보여줍니다. <br><br>

>기대 효과 <br>

사용자는 소비량을 확인하고 지출을 조절 합니다.<br>
소비 패턴을 분석하여 예산을 수정합니다. <br>
지출에대한 메타인지를 강화하여 바람직한 소비를 돕습니다.<br><br>

### 3. 객체 지향적 데이터관리 <br><br>

1. 일일 가계부를 이용해 월별 금액을 만듭니다.
2. 카테고리(사용처)를 이용해 뷰에 이미지나 카테고리이름을 동적으로 표시해야 합니다.
3. 월별, 카테고리별 사용금액과 예산을 이용해 비교하는 작업을 합니다. <br>

이 세가지 기능을 목적에 맞게 하기위해서 3개의 클래스를 각각 만듭니다. 
클래스는 백엔드와 프론트 엔드를 넘나들며 서로 상호작용하고, 데이터들을 비교하고 필요한 값들을 산출합니다.

>역할과 포함된 속성
<br><br>

**MoneyFlow 클래스**

```java
class MoneyFlow {
//    private Categories category;
    private Long id;
    private Long categoriesId;
    private String nowDate;
    private String content;
    private int cost;
    private boolean spend;
```
<br>
일일 가계부를 추가하는 가장 작은단위의 데이터<br>
카테고리, 날짜, 사용처, 금액, 수입or 지출 여부 가 있습니다.
<br><br><br><br>

**Categories 클래스**
```java
class Categories {
    private String imageName;
    private String categoryName;
    private Long id;
```
<br>

![20250322_161339](https://github.com/user-attachments/assets/2aa13ff3-e3f5-4194-916f-f25d21582da5)


리사이클러뷰에 표시할 카테고리이름과 카테고리 아이콘을 관리하는 클래스입니다.<br>
백엔드에서 초기화 데이터를 생성시 참조자들이 한번만 만들어집니다.<br>
추후 카테고리 추가할 필요가 있다면 data.sql에 추가해줍니다.<br>
<br><br><br>

**Predict 클래스**
```java
class Predict  {
    private Long id;
    private int year;
    private int month;
    private Long categoryId;
    private int predict;
    private int monthCost;
```
<br>
MoneyFlow 클래스를 통해 만들어진 객체들을 기반으로 월별, 카테고리별 예산을 관리하는 클래스
<br> 월, 카테고리, 예산, 한달 사용금액 속성이 있습니다.
<br> 예산을 설정거나 수정할때만 사용되는 클래스 입니다.
<br><br><br><br>

### 4.RestAPI
<br><br>
>**스프링부트**
<br>
백엔드를 쉽게 관리하는 스프링 프레임워크
<br>이 스프링의 복잡한 설정을 간소화한 라이브러리
<br>전문가가 아니더라도 쉬운 서비스를 제공하여 빠른 개발에 최적화된 인터페이스입니다.

<br><br>
>**레트로핏**
<br>
프런트엔드에 소속되어 서버에 필요한 String타입을 JSON데이터 타입으로 건내주고 JSON데이터를 받아 자바객체로 바꿔줍니다.

<br><br>
>**RestAPI**
<br>
Json데이터를 옮기는 방식으로 백엔드 프런트엔드간 통신을 사용자에게 제공하는 인터페이스를 총칭합니다.<br>
예를들면 웹사이트에서 댓글하나를 등록하는데에 HTML 페이지 전체를 주고받을 필요는 없습니다.<br>
사용자가 원하는 작업을 일관성있고 빠르게 수행할 수 있도록 개발된 도구입니다. <br>
Spring Boot와 Retrofit을 사용하는 것은 REST API에 특화된 방식입니다.

<br><br><br><br>
#### 사용방법
<br>1. 클라이언트에서 레트로핏을 사용하는 서비스를 설정하고, 통신을 시도하는부분
<br>2. 서버에서 요청을 접수받아 매써드를 처리하고 리턴을 해주는 부분
<br> 이렇게 두개로 나뉩니다.
<br>
<br> 먼저 클라이언트 부분입니다.

```java
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pay_day);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        moneyService = retrofit.create(MoneyService.class);
 ```
<br>레트로핏에 MoneyService라는 클라이언트의 매써드에따라 요청 내용을 분기하는 인터페이스를 붙여줍니다.
<br>레트로핏은 객체가 되어서 프런트 엔드 내에서 호출이 있을때마다 서버와 통신을 할것입니다.
<br>기본 url은 서버가 구동되는 컴퓨터의 포트에 연결해야 합니다.
<br>현재는 h2가상 서버를 내컴퓨터에 구축한것이고, 가상 에뮬레이터를 통해 접속했기때문에 ("http://10.0.2.2:8080") 를 넣어줬습니다.
<br><br><br><br>
```java
interface MoneyService {
    @GET("/money/{date}/contents")
    public Call<List<MoneyFlow>> getMoneyFlowDate(@Path("date") String date);

    @POST("/money/{id}/contents")
    public Call<MoneyFlow> setMoneyFlow(@Path("id") Long id, @Body MoneyFlow moneyFlow);

    @PATCH("/money/{id}/contents")
    public Call<MoneyFlow> update(@Path("id") Long id, @Body MoneyFlow moneyFlow);

    @DELETE("/money/{id}/contents")
    public Call<MoneyFlow> delete(@Path("id") Long id);
```
<br>
요청내용을 분기하는 인터페이스입니다.
<br>@GET,@POST 등과같은 HTTP매쏘드와 요청 url주소에따라 백엔드의 컨트롤러에 정의된 작업메써드와 매핑됩니다.
<br>프런트 엔드 내에서 moneyService.getMoneyFlowDate(날짜)를 호출하면 서버와 통신을 시도합니다.
<br>지금 이 프로젝트는 날짜를 인자값으로 주면 그날짜에 해당하는 지출들을 가져오도록 되어 있습니다.
<br>날짜를 JSON데이터로 주는 방식은 @Path로 url주소값에 써진채로 전달됩니다.
<br>DB의 자원을 수정하거나 생성할땐 Body로 클래스에 참조자나 리스트를 만들어서 건내야합니다. 
<br>무조건 클래스의 속성값이 변하거나 DB가 창조되는 작업이기 때문입니다.
<br>그에비해 삭제나 조회는 아이디값만 주거나 명령만 필요하기때문에 @Body를 줄 필요가 없습니다.
<br><br><br><br>

```java
private void fetchData(String date) {
        moneyService.getMoneyFlowDate(date).enqueue(new Callback<List<MoneyFlow>>() {
            @Override
            public void onResponse(Call<List<MoneyFlow>> call, Response<List<MoneyFlow>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    moneyFlowList.clear();
                    moneyFlowList.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<MoneyFlow>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "no data", Toast.LENGTH_SHORT).show();
            }
        });
    }
```

<br>

![20250322_175242](https://github.com/user-attachments/assets/499ec08f-26a1-4296-b8f8-5a80342e5114)
<br>
백엔드와 통신을 시도하는 부분입니다.<br>
enqueue는 비동기식으로 서버에 통신할때 사용합니다. 웬만하면 거의 모든 통신은 비동기식으로 일어납니다.
<br> 성공한다면 날짜를 주고 그날짜의 가계부 리스트를 받는 JSON데이터 거래 행위가 일어납니다.
<br> 데이터를 수정하거나 생성할때도 마찬가지입니다. 
<br>클래스에 올라온 리스트나 참조자를 불러서 바디를 주고 목적에 맞는 인터페이스의 메써드를 호출하면 됩니다.
<br><br><br><br>
```java
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
```
<br> 원래는 이렇게 통신에 성공한다면 가져온 리스트를 가지고 뷰에 붙이는 작업들이 추가되지만
<br> RestAPI의 핵심내용이 아니라 생략한 내용이니 참고 바랍니다.
<br>
<br> 프런트앤드에서 통신시도를 했으니 백엔드 쪽으로 가겠습니다.
<br><br><br><br>
인텔리제이 개발도구를 사용해 스프링부트 프레임워크를 사용했습니다.
<br>
![20250322_185932](https://github.com/user-attachments/assets/a73ccd9f-94d3-4c9a-99a9-2ac1eeea1a86)
<br>

스프링부트 이니셜라이저로 필요한 라이브러리들을 넣고 파일을 생성후 인텔리제이에서 불러옵니다.
<br>또는 build.gradle 파일에서 라이브러리들을 직접 추가해도 됩니다. 하지만 위의 방식이 더 편합니다.
<br><br><br><br> 여기부턴 백엔드 부분입니다.
```java
@RestController
class contentController {
    @Autowired
    MoneyFlowRepo moneyFlowRepo;
    @Autowired
    CategoriesRepo categoriesRepo;

    //http://localhost:8080/money/2025-02-26/contents 되는것 확인
    @GetMapping("/money/{date}/contents")
    public List<MoneyFlow> getByDate(@PathVariable("date") String date) {
        System.out.println(date);
        LocalDate localDate = LocalDate.parse(date);
        return moneyFlowRepo.findByNowDate(localDate);
    }
```
<br>
@RestController 어노테이션은 RestApi를 사용하는 표시입니다.
<br>
@Autowired는 자동 객체 생성입니다. MoneyFlowRepo moneyFlowRepo=new MoneyFlowRepo(); 
<br>이런식으로 하지않아도 인스턴스를 자동생성 할 수 있습니다.
<br>MoneyFlowRepo, CategoriesRepo 는 JpaRepository를 상속한 인터페이스인데 
<br>엔티티가 DB속 테이블에 저장 및 관리될 수 있도록 합니다.
<br> @GetMapping HTTP메써드와 url 주소가 위에 프런트엔드에서 요청한 것과 동일합니다.
<br> 리스트타입을 리턴하는 getByDate() 메써드의 작업명령서가 도착하고 요청을 접수받았습니다.
<br>
<br><br><br>

![image](https://github.com/user-attachments/assets/e2b5e7ac-cfbe-4adf-89c1-9ede11b0004c)

<br>Repository에서 쉽게 날짜를 찾을수 있도록 String데이터로 도착한 날짜의 데이터타입을 바꿔줬습니다.
<br>그리고 Repository가 지원하는 함수를 사용해 날짜에 해당하는 엔티티클래스의 리스트를 클라이언트 쪽으로 리턴해줍니다.
<br>
<br><br><br>
```java
interface MoneyFlowRepo extends JpaRepository<MoneyFlow, Long> {
    List<MoneyFlow> findByNowDate(LocalDate nowDate);

    @Query(value = "SELECT TO_CHAR(DATE_TRUNC('MONTH', m.now_date), 'YYYY-MM') AS month_start, m.categories_id, SUM(m.cost) AS total_cost " +
            "FROM MONEY_FLOW m " +
            "WHERE YEAR(m.now_date) = :year " +
            "AND MONTH(m.now_date) = :month " +
            "AND m.spend = true " +
            "GROUP BY TO_CHAR(DATE_TRUNC('MONTH', m.now_date), 'YYYY-MM'), m.categories_id " +
            "ORDER BY month_start DESC", nativeQuery = true)
    List<Object[]>getMonthlyCostByCategory(int year,int month);

    @Query("SELECT m FROM MoneyFlow m " +
            "WHERE m.category.id = :categoryId " +
            "AND YEAR(m.nowDate) = :year " +
            "AND MONTH(m.nowDate) = :month " +
            "AND m.spend = true")
    List<MoneyFlow> findByCategoryAndMonth(@Param("categoryId") Long categoryId,
                                           @Param("year") int year,
                                           @Param("month") int month);
}
```
<br>
Respository 인터페이스입니다. findByNowDate처럼 기본적으로 지원하는 함수가 있다면 쓰면되지만 <br>
밑에 함수들처럼 새로운 데이터의 흐름이 필요할땐 가상의 테이블을 참조해야하기때문에 쿼리문을 써야합니다.
<br><br><br><br>
이런식으로 서버와 클라이언트간 통신이 됩니다.
<br>
<br>

![KakaoTalk_20250322_190844531](https://github.com/user-attachments/assets/929d69c7-e138-47d1-a2e5-b40671c3d56b)
<br>






