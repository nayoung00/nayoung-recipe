v22_2 - CSV 문자열을 객체로 전환하는 기능을 도메인 객체로 이전.

## 실습 소스 및 결과

- src/main/java/nayoung/cooknayoung/App.java 변경
- src/main/java/nayoung/cooknayoung/domain/Board.java 변경
- src/main/java/nayoung/cooknayoung/domain/Member.java 변경
- src/main/java/nayoung/cooknayoung/domain/Recipe.java 변경

### 훈련 1: 게시물 데이터를 CSV 문자열로 다루는 코드를 Board 클래스로 옮겨라.

- Board.java
  - CSV 문자열을 가지고 Board 객체를 생성하는 valueOf() 메서드를 추가한다.
  - Board 객체를 가지고 CSV 문자열을 리턴하는 toCsvString() 메서드를 추가한다. 
- App.java
  - loadBoardData() 를 변경한다.
  - saveBoardData() 를 변경한다.
  
### 훈련 2: 회원 데이터를 CSV 문자열로 다루는 코드를 Member 클래스로 옮겨라.

- Member.java
  - CSV 문자열을 가지고 Member 객체를 생성하는 valueOf() 메서드를 추가한다.
  - Member 객체를 가지고 CSV 문자열을 리턴하는 toCsvString() 메서드를 추가한다. 
- App.java
  - loadMemberData() 를 변경한다.
  - saveMemberData() 를 변경한다.
    
### 훈련 3: 요리 데이터를 CSV 문자열로 다루는 코드를 Recipe 클래스로 옮겨라.

- Recipe.java
  - CSV 문자열을 가지고 Recipe 객체를 생성하는 valueOf() 메서드를 추가한다.
  - Recipe 객체를 가지고 CSV 문자열을 리턴하는 toCsvString() 메서드를 추가한다. 
- App.java
  - loadRecipeData() 를 변경한다.
  - saveRecipeData() 를 변경한다.