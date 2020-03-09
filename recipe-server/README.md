# 33_2 - 트랜잭션 관리자를 사용하는 코드를 캡슐화하기


## 작업 소스 및 결과

- src/main/java/kny/cook/sql/TransactionCallback.java 추가
- src/main/java/kny/cook/sql/TransactionTemplate.java 추가
- src/main/java/kny/cook/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/kny/cook/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/kny/cook/servlet/PhotoBoardDeleteServlet.java 변경


### 작업1: 트랜잭션 관리자를 사용하는 코드를 캡슐화하여 별도의 클래스로 분리하라.

- kny.cook.sql.TransactionTemplate 추가
  - 트랜잭션 관리자를 사용하는 코드를 메서드로 정의한다.
- kny.cook.sql.TransactionCallback 인터페이스 추가
  - TransactionTemplate이 작업을 실행할 때 호출할 메서드 규칙을 정의한다.
  - 트랜잭션 작업은 이 규칙에 따라 작성해야 한다.
  
### 작업2: 트랜잭션을 사용할 곳에 TransactionTemplate을 적용하라.

- kny.cook.servlet.PhotoBoardAddServlet 변경
  - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate을 사용한다.
- kny.cook.servlet.PhotoBoardUpdateServlet 변경
  - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate을 사용한다.
- kny.cook.servlet.PhotoBoardDeleteServlet 변경
  - PlatformTransactionManager를 직접 사용하는 대신에 TransactionTemplate을 사용한다.