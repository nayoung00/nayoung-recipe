# 32_4 - Connection을 스레드에 보관하기: 트랜잭션 관리자 도입하기


## 작업 소스 및 결과

- src/main/java/kny/cook/sql/PlatformTransactionManager.java 추가
- src/main/java/kny/cook/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/kny/cook/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/kny/cook/servlet/PhotoBoardDeleteServlet.java 변경
- src/main/java/kny/cook/DataLoaderListener.java 변경
- src/main/java/kny/cook/ServerApp.java 변경


### 작업1: 트랜잭션 제어 코드를 캡슐화 하라.

- kny.cook.sql.PlatformTransactionManager 추가
  - 트랜잭션을 시작시키고, 커밋/롤백하는 메서드를 정의한다.
  
  
### 작업2: PhotoBoardAddServlet에 트랜잭션 관리자를 적용하라.

- kny.cook.servlet.PhotoBoardAddServlet 변경
  - PlatformTransactionManager를 주입 받는다.
  - 트랜잭션 관리자를 통해 트랜잭션을 제어한다.
  
  
### 작업3: PhotoBoardUpdateServlet에 트랜잭션 관리자를 적용하라.

- kny.cook.servlet.PhotoBoardUpdateServlet 변경
  - PlatformTransactionManager를 주입 받는다.
  - 트랜잭션 관리자를 통해 트랜잭션을 제어한다.

  
### 작업4: PhotoBoardDeleteServlet에 트랜잭션 관리자를 적용하라.

- kny.cook.servlet.PhotoBoardDeleteServlet 변경
  - PlatformTransactionManager를 주입 받는다.
  - 트랜잭션 관리자를 통해 트랜잭션을 제어한다.


### 작업5: DataLoaderListener에서 트랜잭션 관리자를 준비하라.

- kny.cook.DataLoaderListener 변경
  - PlatformTransactionManager 객체를 준비한다.


### 작업6: 트랜잭션 관리자를 서블릿에 주입하라.

- kny.cook.ServerApp 변경
  - 맵에서 PlatformTransactionManager 객체를 꺼내 서블릿 객체에 주입한다.

  
### 작업7: /photoboard/add, /photoboard/update, /photoboard/delete을 테스트를 해봐라.