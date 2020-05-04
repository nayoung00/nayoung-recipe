# 46_5 - HttpSession을 활용하여 로그인, 로그아웃 구현하기


## 작업 소스 및 결과

- src/main/java/kny/com/servlet/HeaderServlet.java 변경
- src/main/java/kny/com/servlet/LoginServlet.java 추가
- src/main/java/kny/com/servlet/LogoutServlet.java 추가



### 작업1: 로그인 처리하기

- kny.com.servlet.LoginServlet 변경
  - 사용자 인증(authentication)이 성공하면 HttpSession 보관소에 사용자 정보를 저장한다.
  
### 작업2: 상단 메뉴바에 로그인 사용자의 정보를 출력하기

- kny.com.servlet.HeaderServlet 변경
  - HttpSession에서 로그인 사용자 정보를 꺼내 이름을 출력한다.
  
### 작업3: 로그아웃 처리하기

- kny.com.servlet.LogoutServlet 추가
  - HttpSession을 무효화시킨다.