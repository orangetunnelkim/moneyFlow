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

