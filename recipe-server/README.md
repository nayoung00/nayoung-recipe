# 46_1 - HttpServlet 클래스를 상속 받아서 서블릿 만들기

## 학습목표

- GET/POST 요청을 구분하여 처리할 수 있다.
- HttpServlet과 GenericServlet의 차이점을 이해한다.

## 작업 소스 및 결과

- kny.cook.servlet.BoardAddFormServlet 삭제
- kny.cook.servlet.BoardUpdateFormServlet 삭제
- kny.cook.servlet.XxxServlet 변경


### 작업1: 게시물 입력폼과 입력 처리 서블릿을 한 클래스로 합친다.

- kny.cook.servlet.BoardAddServlet 변경
  - HttpServlet을 상속 받는다.
  - BoardAddFormServlet의 코드를 doGet() 메서드로 옮긴다.
  - BoardAddServlet의 원래 있던 코드를 doPost() 메서드로 옮긴다.
- kny.cook.servlet.BoardListServlet 변경
  - 게시물 입력 링크의 주소를 '/board/add'로 변경한다.
- kny.cook.servlet.BoardAddFormServlet 삭제

### 작업2: 게시물 목록 조회 및 상세 조회, 삭제 서블릿을 변경한다.

- kny.cook.servlet.BoardListServlet 변경
- kny.cook.servlet.BoardDetailServlet 변경
- kny.cook.servlet.BoardDeleteServlet 변경

### 작업3: 게시물 변경폼과 변경 처리 서블릿을 한 클래스로 합친다.

- kny.cook.servlet.BoardUpdateServlet 변경
  - HttpServlet을 상속 받는다.
  - BoardUpdateFormServlet의 코드를 doGet() 메서드로 옮긴다.
  - BoardUpdateServlet의 원래 있던 코드를 doPost() 메서드로 옮긴다.
- kny.cook.servlet.BoardDetailServlet 변경
  - 게시물 입력 링크의 주소를 '/board/update'로 변경한다.
- kny.cook.servlet.BoardUpdateFormServlet 삭제

### 작업4: 나머지 모든 서블릿을 위의 규칙에 따라 변경한다.

- kny.cook.servet.* 변경
