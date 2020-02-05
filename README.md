# 26_1 - 자바 클라이언트 프로젝트 만들기


## 실습 소스 및 결과

- src/main/java/kny/cook/ClientApp.java 추가

## 실습  

### 작업 1: 프로젝트 폴더를 생성하기.

- 'recipe-client' 디렉토리를 생성한다.

### 작업 2: 프로젝트 폴더에 자바 프로젝트로 만들기.

- '$ gradle init' 실행

### 작업 3: 이클립스 IDE로 임포트 하기.

- 'build.gradle' 변경
  - 'eclipse' gradle 플러그인을 추가한다.
  - 'javaCompile'을 설정한다.
- '$ gradle eclipse' 실행
  - gradle을 실행하여 이클립스 설정 파일을 생성한다.
- 이클립스에서 프로젝트 폴더를 임포트한다.

### 작업 4: 프로젝트 시작 클래스를 변경하기.

- 'ClientApp.java' 생성
  - 기존의 'App.java'의 클래스 이름을 변경한다.
  - 소스 코드를 정리한다.
  - '클라이언트 레시피 관리 시스템입니다' 문구를 출력한다.
- 'src/test/java/ClientAppTest.java' 생성
  - 기존의 'AppTest.java'의 클래스 이름을 변경한다.
  - 소스 코드를 정리한다.
- ClientApp.java 를 실행하여 결과를 확인한다.    

