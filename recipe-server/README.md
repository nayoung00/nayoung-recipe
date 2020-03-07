# 31_1 - Connection 개별화하기: 메서드 호출마다 DBMS와 연결하기

## 작업 소스 및 결과

- src/main/java/kny/cook/dao/mariadb/XxxDaoImpl.java 변경
- src/main/java/kny/cook/DataLoaderListener.java 변경


### 작업1: 각 메서드를 호출할 때 DBMS와 연결하라.

- kny.cook.dao.mariadb.XxxDaoImpl 변경
  - 생성자에서 파라미터로 Connection 객체를 받는 대신에 DB 연결 정보를 받는다. 
  - 각 메서드에서 JDBC URL과 username, password를 사용하여 DBMS에 연결한다.
- kny.cook.DataLoaderListener 변경
  - Connection 객체를 생성하지 않는다.
  - 대신 DBMS 연결 정보를 준비하여 DAO 구현체를 생성할 때 넘겨준다.

