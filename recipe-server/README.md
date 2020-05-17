# 47_1 - JSP를 활용하여 서블릿에서 출력 기능을 분리하기  


## 작업 소스 및 결과

- src/main/java/kny/cook/servlet/BoardListServlet.java 변경
- src/main/webapp/board/list.jsp 추가
- src/main/webapp/header.jsp 추가
- src/main/webapp/footer.jsp 추가
- src/main/java/kny/cook/servlet/BoardDetailServlet.java 변경
- src/main/webapp/board/detail.jsp 추가
- src/main/java/kny/cook/servlet/BoardAddServlet.java 변경
- src/main/webapp/board/form.jsp 추가
- src/main/java/kny/cook/servlet/BoardUpdateServlet.java 변경
- src/main/webapp/board/updateform.jsp 추가
- src/main/java/kny/cook/servlet/RecipeListServlet.java 변경
- src/main/webapp/lesson/list.jsp 추가
- src/main/java/kny/cook/servlet/RecipeSearchServlet.java 변경
- src/main/webapp/lesson/search.jsp 추가
- src/main/java/kny/cook/servlet/RecipeAddServlet.java 변경
- src/main/webapp/lesson/form.jsp 추가
- src/main/java/kny/cook/servlet/RecipeDetailServlet.java 변경
- src/main/webapp/lesson/detail.jsp 추가
- src/main/java/kny/cook/servlet/MemberListServlet.java 변경
- src/main/webapp/member/list.jsp 추가
- src/main/java/kny/cook/servlet/MemberSearchServlet.java 변경
- src/main/webapp/member/search.jsp 추가
- src/main/java/kny/cook/servlet/MemberAddServlet.java 변경
- src/main/webapp/member/form.jsp 추가
- src/main/java/kny/cook/servlet/MemberDetailServlet.java 변경
- src/main/webapp/member/detail.jsp 추가
- src/main/java/kny/cook/servlet/PhotoBoardListServlet.java 변경
- src/main/webapp/photoboard/list.jsp 추가
- src/main/java/kny/cook/servlet/PhotoBoardAddServlet.java 변경
- src/main/webapp/photoboard/form.jsp 추가
- src/main/java/kny/cook/servlet/PhotoBoardDetailServlet.java 변경
- src/main/webapp/photoboard/detail.jsp 추가
- src/main/java/kny/cook/servlet/LoginServlet.java 변경
- src/main/webapp/auth/form.jsp 추가
- src/main/webapp/auth/login.jsp 추가


### 작업1: 게시글 목록 출력에 JSP를 적용한다.

- src/main/webapp/board/list.jsp 추가
  - BoardListServlet의 출력을 이 JSP에 작성한다.
- kny.cook.servlet.BoardListServlet 변경
  - 서비스 객체를 통해 출력할 데이터를 준비한다.
  - 출력은 JSP에게 위임한다.
  - 상단 메뉴바와 하단 스크립트 코드 부분을 별도의 JSP로 분리한다.
- src/main/webapp/header.jsp 추가
  - 상단 메뉴를 출력한다.
- src/main/webapp/footer.jsp 추가
  - 하단 스크립트 태그를 출력한다.
  
### 작업2: 게시글 상세 정보 출력에 JSP를 적용한다.

- src/main/webapp/board/detail.jsp 추가
  - BoardDetailServlet의 출력을 이 JSP에 작성한다.
- kny.cook.servlet.BoardDetailServlet 변경
  - 서비스 객체를 통해 출력할 데이터를 준비한다.
  - 출력은 JSP에게 위임한다.

### 작업3: 게시글 입력, 변경폼 출력에 JSP를 적용한다.

- src/main/webapp/board/form.jsp 추가
  - BoardAddServlet의 출력을 이 JSP에 작성한다.
- kny.cook.servlet.BoardAddServlet 변경
  - 출력은 JSP에게 위임한다.
- src/main/webapp/board/updateform.jsp 추가
  - BoardUpdateServlet의 출력을 이 JSP에 작성한다.
- kny.cook.servlet.BoardUpdateServlet 변경
  - 서비스 객체를 통해 출력할 데이터를 준비한다.
  - 출력은 JSP에게 위임한다.
  
### 작업4: 수업관리에 JSP를 적용한다.

- kny.cook.servlet.RecipeListServlet 변경
  - src/main/webapp/lesson/list.jsp 추가
- kny.cook.servlet.RecipeSearchServlet 변경
  - src/main/webapp/lesson/search.jsp 추가
- kny.cook.servlet.RecipeAddServlet 변경
  - src/main/webapp/lesson/form.jsp 추가
- kny.cook.servlet.RecipeDetailServlet 변경
  - src/main/webapp/lesson/detail.jsp 추가
  
### 작업5: 회원관리에 JSP를 적용한다.

- kny.cook.servlet.MemberListServlet 변경
  - src/main/webapp/member/list.jsp 추가
- kny.cook.servlet.MemberSearchServlet 변경
  - src/main/webapp/member/search.jsp 추가
- kny.cook.servlet.MemberAddServlet 변경
  - src/main/webapp/member/form.jsp 추가
- kny.cook.servlet.MemberDetailServlet 변경
  - src/main/webapp/member/detail.jsp 추가
  
### 작업6: 사진게시물 관리에 JSP를 적용한다.

- kny.cook.servlet.PhotoBoardListServlet 변경
  - src/main/webapp/photoboard/list.jsp 추가
- kny.cook.servlet.PhotoBoardAddServlet 변경
  - src/main/webapp/photoboard/form.jsp 추가
- kny.cook.servlet.PhotoBoardDetailServlet 변경
  - src/main/webapp/photoboard/detail.jsp 추가
  
### 작업7: 로그인에 JSP를 적용한다.

- kny.cook.servlet.LoginServlet 변경
  - src/main/webapp/auth/form.jsp 추가
  - src/main/webapp/auth/login.jsp 추가