# 46_2 - 서블릿을 이용하여 스프링 IoC 컨테이너 준비하기


## 작업 소스 및 결과

- kny.cook.ContextLoaderListener 변경
- kny.cook.servlet.AppInitServlet 추가
- src/main/webapp/WEB-INF/web.xml 추가



### 작업1: 서블릿에서 Spring IoC 컨테이너를 준비하여 공유하게 한다.

- kny.cook.ContextLoaderListener 변경
  - 리스너를 비활성화시킨다.
    - @WebListener 애노테이션을 주석으로 막는다.
- kny.cook.servlet.AppInitServlet 추가
  - 웹 애플리케이션이 시작할 때 자동 생성되게 한다.
- BoardAddFormServlet의 코드를 doGet() 메서드로 옮긴다.
    - @WebServlet 애노테이션에 loadOnStartup 속성 추가
  - service() 또는 doXxx() 메서드는 오버라이딩 하지 않는다.
    - 왜? 클라이언트 요청을 처리하는 일을 하지 않는다.
    - 이 클래스는 다른 서블릿이 사용할 IoC 컨테이너를 준비한다.

