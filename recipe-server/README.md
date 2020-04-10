# 44_2 - 출력 콘텐트에 HTML 형식 적용하기


## 작업 소스 및 결과

- src/main/java/kny/cook/dao/RecipeDao.java 변경 
- src/main/java/kny/cook/dao/MemberDao.java 변경
- src/main/java/kny/cook/servlet/BoardAddFormServlet.java 추가
- src/main/java/kny/cook/servlet/BoardUpdateFormServlet.java 추가
- src/main/java/kny/cook/servlet/BoardXxxServlet.java 변경
- src/main/java/kny/cook/servlet/MemberAddFormServlet.java 추가
- src/main/java/kny/cook/servlet/MemberXxxServlet.java 변경
- src/main/java/kny/cook/servlet/RecipeAddFormServlet.java 추가
- src/main/java/kny/cook/servlet/RecipeXxxServlet.java 변경
- src/main/java/kny/cook/servlet/PhotoBoardAddFormServlet.java 추가
- src/main/java/kny/cook/servlet/PhotoBoardXxxServlet.java 변경
- src/main/java/kny/cook/servlet/LoginFormServlet.java 추가
- src/main/java/kny/cook/servlet/LoginServlet.java 변경
- src/main/java/kny/cook/ServerApp.java 변경


### 작업1: 게시글 목록을 출력할 때 HTML 형식으로 콘텐트를 출력한다.

- kny.cook.servlet.BoardListServlet 변경

### 작업2: 웹브라우저에게 게시글 데이터 입력을 요구한다.

- kny.cook.servlet.BoardAddFormServlet 추가
  - 웹브라우저에게 게시글 데이터 입력을 요구하는 HTML을 보낸다.
- kny.cook.servlet.BoardListServlet 변경
  - /board/addForm 을 요청하는 링크를 추가한다.


### 작업3: 웹브라우저가 보낸 데이터 받기

- kny.cook.ServerApp 변경
  - request-uri에서 자원의 경로와 데이터를 분리한다.
  - 예) /board/add?title=aaaa
  - 자원의 경로: /board/add
  - 데이터: title=aaaa

### 작업4: 웹브라우저가 보낸 게시글 데이터 저장하기

- kny.cook.servlet.BoardAddServlet 변경
  - 웹브라우저가 보낸 게시글을 입력한다.
  - 웹브라우저에게 게시글 입력 결과를 보낸다.

### 작업5: 게시글 상세 정보를 출력하기

- kny.cook.servlet.BoardDetailServlet 변경
  - 웹브라우저가 보낸 번호의 게시글을 가져온다.
  - 웹브라우저에게 게시글 상세 정보를 HTML 형식으로 만들어 보낸다.
- kny.cook.servlet.BoardListServlet 변경
  - /board/detail 을 요청하는 링크를 추가한다.  

### 작업6: 게시글 삭제하기

- kny.cook.servlet.BoardDeleteServlet 변경
  - 웹브라우저가 보낸 번호의 게시글을 삭제한다.
  - 웹브라우저에게 게시글 삭제 결과를 HTML 형식으로 만들어 보낸다.
- kny.cook.servlet.BoardDetailServlet 변경
  - /board/delete 을 요청하는 링크를 추가한다.

### 작업7: 게시글 변경폼 만들기

- kny.cook.servlet.BoardDetailServlet 변경
  - /board/updateForm 을 요청하는 링크를 추가한다.
- kny.cook.servlet.BoardUpdateFormServlet 추가
  - 웹브라우저에게 게시글 데이터 변경을 요구하는 HTML을 보낸다.

### 작업8: 게시글 변경하기

- kny.cook.servlet.BoardUpdateServlet 변경
  - 웹브라우저가 보낸 게시글을 변경한다.
  - 웹브라우저에게 게시글 변경 결과를 보낸다.

### 작업9: 회원 관리 서블릿을 모두 변경하기

- kny.cook.dao.MemberDao 변경
  - default 메서드를 추상 메서드로 전환한다.
- kny.cook.servlet.MemberAddFormServlet 추가
- kny.cook.servlet.MemberXxxServlet 변경
- kny.cook.ServerApp 변경

### 작업10: 수업 관리 서블릿을 모두 변경하기

- kny.cook.dao.RecipeDao 변경
  - default 메서드를 추상 메서드로 전환한다.
- kny.cook.servlet.RecipeAddFormServlet 추가
- kny.cook.servlet.RecipeXxxServlet 변경

### 작업11: 사진게시글 관리 서블릿을 모두 변경하기

- kny.cook.servlet.PhotoBoardAddFormServlet 추가
- kny.cook.servlet.PhotoBoardXxxServlet 변경

### 작업12: 로그인 서블릿을 모두 변경하기

- kny.cook.servlet.LoginFormServlet 추가
- kny.cook.servlet.LoginServlet 변경