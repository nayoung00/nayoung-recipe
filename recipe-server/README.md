# 41_2 - 관리하기 쉽게 Spring IoC 설정 파일(Java Config)을 분리하기


## 작업 소스 및 결과

- src/main/java/kny/cook/AppConfig.java 변경
- src/main/java/kny/cook/DatabaseConfig.java 추가
- src/main/java/kny/cook/MybatisConfig.java 추가
- src/main/java/kny/cook/ContextLoaderListener.java 변경


### 작업1: 데이터베이스 관련 설정을 분리하기

- kny.cook.DatabaseConfig 추가
  - AppConfig에서 데이터베이스 관련 객체 생성 코드를 가져온다.
- kny.cook.AppConfig 변경
  
### 작업2: Mybatis 관련 설정을 분리하기

- kny.cook.MybatisConfig 추가
  - AppConfig에서 Mybatis 관련 객체 생성 코드를 가져온다.
- kny.cook.AppConfig 변경

### 작업3: Spring IoC 컨테이너를 생성할 때 Java Config를 모두 지정한다.

- kny.cook.ContextLoaderListener 변경
  - Spring IoC 컨테이너 생성 코드를 변경한다.
  
### 작업4: @Configuration 애노테이션을 사용하여 Java Config 를 설정한다.

- kny.cook.DatabaseConfig 변경
  - @Configuration 애노테이션을 붙인다.
- kny.cook.MybatisConfig 변경
  - @Configuration 애노테이션을 붙인다.
- kny.cook.ContextLoaderListener 변경
  - Spring IoC 컨테이너를 생성할 때 Java Config로 AppConfig 만 지정한다.