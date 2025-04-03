# 일정, 가계부 프로그램
<br>

>스프링부트 프레임워크와 안드로이드 스튜디오를 Retrofit으로 연결해 RESTFULAPI를 사용하는 프로젝트입니다.

### 프로젝트 기간 2025.02.25 ~ 2025.04.01 (총 5주)
### 팀원 김지욱(본인), 백건우, [엄정현](https://github.com/natanal-hyun/sometodo-app)
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
     * aws ec2 RDS
     * jwt기반 로그인 및 인증기능
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
일별, 월(카테고리)별 지출들을 각각 확인 할 수 있게 하였습니다.

![메인화면](https://github.com/user-attachments/assets/ff607754-0909-4f4b-9bf9-a804705dc098) ![월별지출](https://github.com/user-attachments/assets/f71811dd-6916-464a-bdff-5f1486649944)

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




#### 예산설정 및 DB반영
#### progressBar를 통한 시각화
