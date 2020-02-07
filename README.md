# 26_6 - 커맨드 패턴을 적용하여 요청 처리 메서드 객체화

## 실습 소스 및 결과

- src/main/java/kny/cook/servlet 패키지 생성
- src/main/java/kny/cook/servlet/Servlet.java 추가
- src/main/java/kny/cook/servlet/BoardListServlet.java 추가
- src/main/java/kny/cook/servlet/BoardAddServlet.java 추가
- src/main/java/kny/cook/servlet/BoardDetailServlet.java 추가
- src/main/java/kny/cook/servlet/BoardUpdateServlet.java 추가
- src/main/java/kny/cook/servlet/BoardDeleteServlet.java 추가
- src/main/java/kny/cook/servlet/RecipeListServlet.java 추가
- src/main/java/kny/cook/servlet/RecipeAddServlet.java 추가
- src/main/java/kny/cook/servlet/RecipeDetailServlet.java 추가
- src/main/java/kny/cook/servlet/RecipeUpdateServlet.java 추가
- src/main/java/kny/cook/servlet/RecipeDeleteServlet.java 추가
- src/main/java/kny/cook/servlet/MemberListServlet.java 추가
- src/main/java/kny/cook/servlet/MemberAddServlet.java 추가
- src/main/java/kny/cook/servlet/MemberDetailServlet.java 추가
- src/main/java/kny/cook/servlet/MemberUpdateServlet.java 추가
- src/main/java/kny/cook/servlet/MemberDeleteServlet.java 추가
- src/main/java/kny/cook/ServerApp.java 변경

## 실습  

### 훈련 1: 커맨드 패턴의 인터페이스 정의한다.

- kny.cook.servlet 패키지를 생성한다.
- kny.cook.servlet.Servlet 인터페이스를 정의한다.

### 훈련 2: 각각의 요청 처리 메서드를 인터페이스 규칙에 따라 클래스를 정의한다.
 
- listBoard()를 BoardListServlet 클래스로 정의한다.
- addBoard()를 BoardAddServlet 클래스로 정의한다.
- detailBoard()를 BoardDetailServlet 클래스로 정의한다.
- updateBoard()를 BoardUpdateServlet 클래스로 정의한다.
- deleteBoard()를 BoardDeleteServlet 클래스로 정의한다.
- listMember()를 MemberListServlet 클래스로 정의한다.
- addMember()를 MemberAddServlet 클래스로 정의한다.
- detailMember()를 MemberDetailServlet 클래스로 정의한다.
- updateMember()를 MemberUpdateServlet 클래스로 정의한다.
- deleteMember()를 MemberDeleteServlet 클래스로 정의한다.
- listRecipe()를 RecipeListServlet 클래스로 정의한다.
- addRecipe()를 RecipeAddServlet 클래스로 정의한다.
- detailRecipe()를 RecipeDetailServlet 클래스로 정의한다.
- updateRecipe()를 RecipeUpdateServlet 클래스로 정의한다.
- deleteRecipe() 를 RecipeDeleteServlet 클래스로 정의한다.
