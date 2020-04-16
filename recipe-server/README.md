# 44_3 - Apache HttpComponents 라이브러리를 이용하여 웹서버 구현하기


## 작업 소스 및 결과

- build.gradle 변경
- src/main/java/kny/cook/ServerApp.java 변경
- src/main/java/kny/cook/servlet/XxxServlet.java 변경


### 작업1: Apache HttpComponents 라이브러리를 프로젝트에 적용한다.

- search.maven.org 에서 라이브러리 정보 알아낸다.
  - 'httpclient5' 로 검색한다.
- build.gradle 변경
  - 의존 라이브러리를 추가한다.
- `gradle eclipse` 실행
  - 이클립스 설정 파일을 갱신한다.
- 이클립스에서 프로젝트 refresh 한다.

### 작업2: HTTP 요청을 받을 때 HttpComponents 라이브러리에 있는 클래스를 사용하여 처리한다.

- kny.cook.ServerApp 변경

### 작업3: 서블릿의 request handler의 파라미터를 변경한다.

- kny.cook.servlet.XxxServlet 변경
  - request handler의 파라미터를 PrintStream 에서 PrintWriter로 바꾼다.
 