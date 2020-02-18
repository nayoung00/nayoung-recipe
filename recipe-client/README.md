# 29_2 - DB 커넥션 객체 공유하기


## 작업 소스 및 결과

- src/main/java/kny/cook/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/RecipeDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/kny/cook/ClientApp.java 변경


### 작업1: 한 개의 DB 커넥션 객체를 DAO 모두가 공유하여 사용하라.

- kny.cook.ClientApp 변경한다.
- kny.cook.dao.mariadb.XxxDaoImpl 변경한다.

