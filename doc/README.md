v22_3 - 파일 포맷으로 JSON 도입하기.

## 실습 소스 및 결과

- build.gradle 변경
- src/main/java/com/eomcs/lms/App.java 변경

### 훈련 1: Gradle 스크립트 파일(build.gradle)에 Google JSON 라이브러리를 추가하라.

- mvnrepository.com 에서 라이브러리 검색.
  - json.org 사이트에서 자바 라이브러리 확인.
  - 'gson' 키워드로 검색.
- build.gradle 을 편집한다.
  - 의존 라이브러리 블록(dependencies{}) 에 gson 정보를 추가한다.
- 이클립스 설정 파일을 갱신한다.
  - 'gradle eclipse'를 실행
  - 이클립스에서 해당 프로젝트를 'refresh'.
  - 'Referenced Libraries' 노드에서 gson 라이브러리 파일이 추가된 것을 확인.
  
### 훈련 2: 게시물 데이터를 저장할 때 JSON 형식을 사용하라.
  
- App.java
  - saveBoardData()를 변경한다.
  - loadBoardData()를 변경한다.
  
### 훈련 3: 회원 데이터를 저장할 때 JSON 형식을 사용하라.
  
- App.java
  - saveMemberData()를 변경한다.
  - loadMemberData()를 변경한다.
  
### 훈련 4: 수업 데이터를 저장할 때 JSON 형식을 사용하라.
  
- App.java
  - saveLessonData()를 변경한다.
  - loadLessonData()를 변경한다.

### 훈련 5: Arrays의 메서드를 활용하여 배열을 List 객체로 만들어라.

- App.java
  - 해당 부분의 코드를 변경한다. 
