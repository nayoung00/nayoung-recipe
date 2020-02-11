# 26_9 - 파일에 데이터를 저장시 JSON 형식 사용


## 작업 소스 및 결과

- src/main/java/kny/cook/dao/json 패키지 추가
- src/main/java/kny/cook/dao/json/AbstractJsonFileDao.java 추가
- src/main/java/kny/cook/dao/json/BoardJsonFileDao.java 변경
- src/main/java/kny/cook/dao/json/RecipeJsonFileDao.java 변경
- src/main/java/kny/cook/dao/json/MemberJsonFileDao.java 변경
- src/main/java/kny/cook/servlet/BoardXxxServlet.java 변경
- src/main/java/kny/cook/servlet/RecipeXxxServlet.java 변경
- src/main/java/kny/cook/servlet/MemberXxxServlet.java 변경
- src/main/java/kny/cook/DataLoaderListener.java 변경
- src/main/java/kny/cook/ServerApp.java 변경


### 작업 1: JSON 형식으로 데이터를 저장하고 로딩할 수퍼 클래스를 정의하라.

- kny.cook.dao.json 패키지를 생성한다.
- kny.cook.dao.json.AbstractJsonFileDao 클래스를 생성한다.

### 작업 2: BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- kny.cook.dao.BoardObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 작업 3: RecipeObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- kny.cook.dao.RecipeObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 작업 4: MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하라.

- kny.cook.dao.MemberObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 작업 5: XxxObjectFileDao 대신 XxxJsonFileDao를 사용하도록 서블릿 클래스를 변경하라.

- kny.cook.servlet.BoardXxxServlet 변경한다.
- kny.cook.servlet.RecipeXxxServlet 변경한다.
- kny.cook.servlet.MemberXxxServlet 변경한다.

### 작업 6: 애플리케이션이 시작할 때 XxxObjectFileDao 대신 XxxJsonFileDao를 준비하라.

- kny.cook.DataLoaderListener 변경한다.

### 작업 7: XxxObjectFileDao 대신 XxxJsonFileDao를 서블릿 객체에 주입하라.

- com.eocms.lms.ServerApp 변경한다.