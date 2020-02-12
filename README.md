# 26_10 - 인터페이스를 이용하여 DAO 호출 규칙 통일

### 인터페이스

- 사용자(예: Servlet)와 피사용자(예: DAO) 사이에 호출 규칙을 정의하는 문법이다.
- 호출 규칙을 정의해 두면 사용자 입장에서 피사용자측이 다른 객체로 바뀌더라도 
  코드를 변경할 필요가 없기 때문에 유지보수에 좋다.
- 피사용자 객체를 정의하는 개발자 입장에서도 인터페이스 규칙에 따라 만들면 되기 때문에 
  코드 작성과 테스트가 용이하다.


## 실습 소스 및 결과

- src/main/java/kny/cook/dao/BoardDao.java 인터페이스 추가
- src/main/java/kny/cook/dao/BoardObjectFileDao.java 변경
- src/main/java/kny/cook/dao/json/BoardJsonFileDao.java 변경
- src/main/java/kny/cook/dao/RecipeDao.java 인터페이스 추가
- src/main/java/kny/cook/dao/RecipeObjectFileDao.java 변경
- src/main/java/kny/cook/dao/json/RecipeJsonFileDao.java 변경
- src/main/java/kny/cook/dao/MemberDao.java 인터페이스 추가
- src/main/java/kny/cook/dao/MemberObjectFileDao.java 변경
- src/main/java/kny/cook/dao/json/MemberJsonFileDao.java 변경
- src/main/java/kny/cook/servlet/BoardXxxServlet.java 변경
- src/main/java/kny/cook/servlet/RecipeXxxServlet.java 변경
- src/main/java/kny/cook/servlet/MemberXxxServlet.java 변경
- src/main/java/kny/cook/DataLoaderListener.java 변경
- src/main/java/kny/cook/ServerApp.java 변경


### 작업 1: BoardXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현하라.

- kny.cook.dao.BoardDao 인터페이스 생성한다.
- kny.cook.dao.BoardObjectFileDao 클래스를 변경한다.
  - BoardDao 인터페이스를 구현한다.
- kny.cook.dao.json.BoardJsonFileDao 클래스를 변경한다.
  - BoardDao 인터페이스를 구현한다.
- kny.cook.servlet.BoardXxxServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 BoardDao 인터페이스로 변경한다.
- kny.cook.DataLoaderListener 변경한다.
- kny.cook.ServerApp 변경한다.
 
### 작업 2: RecipeXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현하라.

- kny.cook.dao.RecipeDao 인터페이스 생성한다.
- kny.cook.dao.RecipeObjectFileDao 클래스를 변경한다.
  - RecipeDao 인터페이스를 구현한다.
- kny.cook.dao.json.RecipeJsonFileDao 클래스를 변경한다.
  - RecipeDao 인터페이스를 구현한다.
- kny.cook.servlet.RecipeXxxServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 RecipeDao 인터페이스로 변경한다.
- kny.cook.DataLoaderListener 변경한다.
- kny.cook.ServerApp 변경한다.

### 작업 3: MemberXxxServlet이 사용할 DAO 호출 규칙을 정의하고 구현하라.

- kny.cook.dao.MemberDao 인터페이스 생성한다.
- kny.cook.dao.MemberObjectFileDao 클래스를 변경한다.
  - MemberDao 인터페이스를 구현한다.
- kny.cook.dao.json.MemberJsonFileDao 클래스를 변경한다.
  - MemberDao 인터페이스를 구현한다.
- kny.cook.servlet.MemberXxxServlet 클래스를 변경한다.
  - DAO 레퍼런스 타입을 MemberDao 인터페이스로 변경한다.
- kny.cook.DataLoaderListener 변경한다.
- kny.cook.ServerApp 변경한다.