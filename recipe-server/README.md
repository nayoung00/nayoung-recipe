# 46_4 - 포워딩과 인클루딩 활용


## 실습 소스 및 결과

- src/main/java/kny/cook/servlet/HeaderServlet.java 추가
- src/main/java/kny/cook/servlet/FooterServlet.java 추가
- src/main/java/kny/cook/servlet/XxxServlet 변경


### 작업1: 오류가 발생하면 ErrorServlet 으로 포워딩 한다.

- kny.cook.servlet.XxxServlet 변경
  - 정상적으로 실행했을 경우 목록 화면으로 리다이렉트 한다.
  - 오류가 발생했을 경우 ErrorServlet 으로 포워딩 한다.
- kny.cook.servlet.ErrorServlet 변경
  - ServletRequest 보관소에서 오류 객체를 꺼내 오류 내용을 출력한다. 
  - doGet() 대신 GET/POST 모두 처리할 수 있는 service()를 오버라이딩 한다.
  
### 작업2: 화면의 상단과 하단을 출력할 서블릿을 만들고 각 페이지에 포함한다.

- kny.cook.servlet.HeaderServlet 추가
- kny.cook.servlet.FooterServlet 추가
- kny.cook.servlet.XxxServlet 변경
