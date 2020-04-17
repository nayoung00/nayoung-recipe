# 45_1 - JavaEE 의 Servlet 기술 도입하기

## 작업 소스 및 결과

- build.gradle 변경
- src/main/java/kny/cook/ContextLoaderListener.java 변경
- src/main/java/kny/cook/context 폴더 삭제
- src/main/java/kny/cook/servlet/XxxServlet.java 변경
- src/main/java/kny/cook/ServerApp.java 삭제


### 작업1: 서블릿 컨테이너를 설치 및 설정한다.

- tomcat.apache.org 사이트에서 서블릿 컨테이너를 다운로드 받는다.
- 특정 폴더에 압축을 풀고, 설정한다.
  - 관리자 ID/PWD를 등록한다.
    - $톰캣홈/conf/tomcat-users.xml
  - 관리자 로그인을 활성화시킨다.
    - $톰캣홈/conf/Catalina/localhost/manager.xml
- 톰캣 서버를 실행하고 웹 브라우저를 통해 접속 확인한다.

### 작업2: JavaEE Servlet 기술을 사용하기 위한 라이브러리를 프로젝트에 적용한다.

- build.gradle 변경
  - search.maven.org 에서 'servlet-api'를 검색한다.
  - 의존 라이브러리 블록에 추가한다.
  - 의존 라이브러리 옵션은 compileOnly로 설정한다.
  - 왜? 'servlet-api'는 컴파일할 때만 사용할 것이기 때문이다. 
- 'gradle eclipse' 실행
  - 이클립스 설정 파일을 갱신한다.
- 이클립스 IDE의 프로젝트를 refresh 한다.

### 작업3: JavaEE의 Servlet 기술을 사용하여 Spring IoC 컨테이너를 준비한다.

- kny.cook.ContextLoaderListener 변경
  - Servlet 기술에게 제공하는 ServletContextListener를 구현한다.
  - Spring IoC 컨테이너를 준비한다. 
- kny.cook.context 패키지 및 하위 클래스 삭제

### 작업4: 기존의 서블릿 클래스를 JavaEE의 Servlet 기술을 적용하여 변경한다.

- kny.cook.servlet.XxxServlet 변경
- kny.cook.ServerApp 삭제

### 작업5: 웹애플리케이션을 빌드 한다.

- build.gradle 변경
  - 웹애플리케이션 배치 파일을 생성하기 위해 'war' 플러그인을 추가한다.
- 'gradle build'를 실행한다.
  - 'build/libs/recipe-server.war' 파일이 생성된다.

### 작업6: 톰캣 서버에 배치한다.

- $톰캣홈/webapps/ 폴더에 war 파일을 놓는다.
- 톰캣 서버를 다시 시작한다.
  - 톰캣 서버는 recipe-server.war 파일과 
    동일한 이름으로 폴더를 만들고 압축 푼다.
  - 예) $톰캣홈/webapp/recipe-server

### 작업7: 웹애플리케이션을 실행한다.

- /board/list 실행
  - http://localhost:포트번호/웹애플리케이션이름/board/list
  - 웹애플리케이션 이름은 webapps/ 폴더에 압축을 푼 디렉토리 이름이다.
  - 예) http://localhost:9999/recipe-server/board/list
- 새글 링크 클릭
  - 화면을 찾을 수 없다.
  - 이유? 링크에 절대 경로를 사용한다. "/board/addForm"
  - 웹 애플리케이션을 배치하면 기본으로 프로젝트명이 웹 애플리케이션 이름이 된다.
  - 이 웹 애플리케이션에 있는 서블릿을 실행하려면 항상 
    웹 애플리케이션 이름을 사용하여 실행해야 한다.
  - 예) /recipe-server/board/addForm
  - 해결책? 상대 경로를 지정하라!

### 작업8: URL 링크를 상대로 경로로 바꾼다.

- kny.cook.servlet.XxxServlet 변경
  - 절대 경로 대신 상대 경로로 바꾼다.
