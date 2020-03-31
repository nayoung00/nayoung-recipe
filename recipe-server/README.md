# 37_2 - Mybatis를 이용하여 DAO 구현체 자동 생성하기


## 작업 소스 및 결과

- src/main/java//kny/cook/service/impl/BoardServiceImpl2.java 추가
- src/main/java//kny/cook/DataLoaderListener.java 변경


### 작업1: BoardServiceImpl 에 대해서 Mybatis DAO 자동 생성기를 적용한다.

- kny.cook.service.impl.BoardServiceImpl2 추가
  - BoardDao 구현체를 직접 주입하는 대신에 SqlSessionFactory를 주입한다.
  - BoardDao를 사용할 때 마다 SqlSession 객체를 통해 만들어 쓴다. 
- kny.cook.DataLoaderListener 변경
  - BoardService 구현체를 BoardServiceImpl2로 교체한다.
