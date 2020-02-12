# 26_11 - 서버에서 제공한 프록시 객체 사용하여 데이터 처리

## 작업 소스 및 결과

- src/main/java/kny/cook/dao/BoardDao.java 추가
- src/main/java/kny/cook/dao/proxy/XxxDaoProxy.java 추가
- src/main/java/kny/cook/handler/XxxCommand.java 변경
- src/main/java/kny/cook/ClientApp.java 변경


### 작업 1: 서버 프로젝트(32_11)에서 DAO 프록시 클래스를 가져오라.

- kny.cook.dao.XxxDao 인터페이스를 가져온다.
- kny.cook.dao.proxy 패키지와 그 하위 클래스를 모두 가져온다.

### 작업 2: BoardXxxCommand 객체가 BoardDaoProxy 객체를 사용하여 데이터를 처리하게 하라.

- kny.cook.handler.BoardXxxCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다.
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.
- kny.cook.ClientApp 변경한다.
  - BoardDaoProxy 객체를 생성한다.
  - BoardXxxCommand 객체에 주입한다.

### 작업 3: RecipeXxxCommand 객체가 RecipeDaoProxy 객체를 사용하여 데이터를 처리하게 하라.

- kny.cook.handler.RecipeXxxCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다.
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.
- kny.cook.ClientApp 변경한다.
  - RecipeDaoProxy 객체를 생성한다.
  - RecipeXxxCommand 객체에 주입한다.
  
### 작업 4: MemberXxxCommand 객체가 MemberDaoProxy 객체를 사용하여 데이터를 처리하게 하라.

- kny.cook.handler.MemberXxxCommand 클래스를 변경한다.
  - 입출력 스트림 필드를 제거한다.
  - 생성자에서 프록시 객체를 받는다.
  - 프록시 객체를 사용하여 데이터를 처리한다.
- kny.cook.ClientApp 변경한다.
  - MemberDaoProxy 객체를 생성한다.
  - MemberXxxCommand 객체에 주입한다.
