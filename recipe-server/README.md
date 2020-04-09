# 43_2 - Log4j 2.x 를 사용하여 애플리케이션 로그 처리하기

## 작업 소스 및 결과

- build.gradle 변경
- src/main/resources/log4j2.xml 추가
- src/main/java/kny/cook/AppConfig.java 변경
- src/main/java/kny/cook/DatabaseConfig.java 변경
- src/main/java/kny/cook/MybatisConfig.java 변경
- src/main/java/kny/cook/ContextLoaderListener.java 변경
- src/main/java/kny/cook/ServerApp.java 변경


### 작업1: Log4j 2.x 라이브러리를 추가한다.

- 라이브러리 정보 알아내기
    - `mvnrepository.com`에서 `log4j-core`를 검색한다.
- build.gradle
    - `log4j` 라이브러리 정보를 추가한다.
    - `$ gradle eclipse`를 실행하여 이클립스 설정 파일을 갱신한다.
    - 이클립스 워크스페이스에 로딩되어 있는 클래스를 갱신한다.


### 작업2: Log4j 설정 파일을 추가한다.

- src/main/resources/log4j2.xml 추가
  - 자바 classpath 루트에 log4j 설정 파일을 둔다.
  - log4j의 출력 범위와 출력 대상, 출력 형식을 설정하는 파일이다.


### 작업3: 각 클래스의 로그 출력을 Log4j2로 전환한다.

- kny.cook.ServerApp 변경
- kny.cook.ContextLoaderListener 변경
- kny.cook.AppConfig 변경
- kny.cook.DatabaseConfig 변경
- kny.cook.MybatisConfig 변경

### 작업4: Mybatis에 log4j2를 설정한다.

- kny.cook.MybatisConfig 변경
  - org.apache.ibatis.logging.LogFactory.useLog4J2Logging() 호출
  - 즉 log4j 기능을 활성화시킨다.



