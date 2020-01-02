# 12 - 다형성과 형변환 응용

- 다형적 변수를 활용하여 다양한 타입의 데이터를 다룰 수 있다.
- 형변환을 이해하고 다룰 수 있다.


- src/main/java/com/eomcs/lms/handler/RecipeList.java 삭제
- src/main/java/com/eomcs/lms/handler/MemberList.java 삭제
- src/main/java/com/eomcs/lms/handler/BoardList.java 삭제
- src/main/java/com/eomcs/lms/handler/ArrayList.java 추가
- src/main/java/com/eomcs/lms/handler/RecipeHandler.java 변경
- src/main/java/com/eomcs/lms/handler/MemberHandler.java 변경
- src/main/java/com/eomcs/lms/handler/BoardHandler.java 변경

## 실습

### 작업1) Recipe, Member, Board를 모두 다룰 수 있는 List 클래스를 만들라.

- ArrayList.java
    - RecipeList, MemberList, BoardList 클래스를 합쳐 한 클래스로 만든다.
- RecipeHandler.java
    - `ArrayList` 클래스를 사용하여 데이터를 처리한다.
- MemberHandler.java
    - `ArrayList` 클래스를 사용하여 데이터를 처리한다.
- BoardHandler.java
    - `ArrayList` 클래스를 사용하여 데이터를 처리한다.
