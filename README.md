# 26_7 - 데이터 처리 코드를 별도의 클래스로 정의하여 객체화 시키기


### DAO(Data Access Object)

- 데이터 처리 역할을 수행하는 객체이다.
- 데이터 처리 방식을 캡슐화(=추상화=클래스로 정의)하여 객체의 사용을 일관성 있게 만든다.
  - 즉 데이터 처리 방식(배열, 스택, 큐, 맵, 파일, 데이터베이스 등)을 
    클래스로 포장(캡슐화)하면 데이터 처리 방식에 상관없이 메서드 사용을 통일할 수 있다.
 

## 작업 소스 및 결과

- src/main/java/kny/cook/dao 패키지 생성
- src/main/java/kny/cook/dao/BoardObjectFileDao.java 추가
- src/main/java/kny/cook/dao/RecipeObjectFileDao.java 추가
- src/main/java/kny/cook/dao/MemberObjectFileDao.java 추가
- src/main/java/kny/cook/ServerApp.java 변경


### 작업 1: 게시물 데이터를 처리하는 DAO 클래스 정의하기.   

- kny.cook.dao 패키지를 생성한다.
- kny.cook.BoardObjectFileDao 클래스를 정의한다.

### 작업 2: BoardObjectFileDao 객체 적용하기.

- kny.cook.DataLoaderListener 를 변경한다.
  - 게시물 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 BoardObjectFileDao 객체를 생성한다.
- kny.cook.ServerApp 을 변경한다.
  - Map에서 BoardObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- BoardXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 BoardObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 BoardObjectFileDao 객체를 통해 처리한다.
  
  
### 작업 3: 수업 데이터를 처리하는 DAO 클래스를 정의하고 적용하기.

- kny.cook.RecipeObjectFileDao 클래스를 정의한다.
- kny.cook.DataLoaderListener 를 변경한다.
  - 수업 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 RecipeObjectFileDao 객체를 생성한다.
- kny.cook.ServerApp 을 변경한다.
  - Map에서 RecipeObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- RecipeXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 RecipeObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 RecipeObjectFileDao 객체를 통해 처리한다.

### 작업 4: 회원 데이터를 처리하는 DAO 클래스를 정의하고 적용하기.

- kny.cook.MemberObjectFileDao 클래스를 정의한다.
- kny.cook.DataLoaderListener 를 변경한다.
  - 회원 데이터를 로딩하고 저장하는 기존 코드를 제거한다.
  - 대신에 MemberObjectFileDao 객체를 생성한다.
- kny.cook.ServerApp 을 변경한다.
  - Map에서 MemberObjectFileDao를 꺼내 관련 커맨드 객체에 주입한다.
- MemberXxxServlet 을 변경한다.
  - 생성자에서 List 객체를 받는 대신에 MemberObjectFileDao 객체를 받는다.
  - 데이터를 저장하고, 조회하고, 변경하고, 삭제할 때 MemberObjectFileDao 객체를 통해 처리한다.
  
  