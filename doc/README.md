# 20 - `커맨드(Command)` 디자인 패턴을 적용하기

## 실습 소스 및 결과

- src/main/java/nayoung/cooknayoung/handler/Command.java 추가
- src/main/java/nayoung/cooknayoung/handler/LecipeAddCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/LecipeListCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/LecipeDetailCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/LecipeUpdateCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/LecipeDeleteCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/MemberAddCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/MemberListCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/MemberDetailCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/MemberUpdateCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/MemberDeleteCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/BoardAddCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/BoardListCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/BoardDetailCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/BoardUpdateCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/BoardDeleteCommand.java 추가
- src/main/java/nayoung/cooknayoung/handler/LecipeHandler.java 삭제
- src/main/java/nayoung/cooknayoung/handler/MemberHandler.java 삭제
- src/main/java/nayoung/cooknayoung/handler/BoardHandler.java 삭제
- src/main/java/nayoung/cooknayoung/App.java 변경

## 실습

### 훈련1. 메서드를 호출하는 쪽과 실행 쪽 사이의 규칙을 정의하라.

- Command.java
    - `App` 클래스와 명령을 처리하는 클래스 사이의 호출 규칙을 정의한다.

### 훈련2. 명령을 처리하는 각 메서드를 객체로 분리하라.

- LecipeHandler.java
    - 레시피 CRUD 각 기능을 `Command` 규칙에 따라 객체로 분리한다.
- MemberHandler.java
    - 멤버CRUD 각 기능을 `Command` 규칙에 따라 객체로 분리한다.
- BoardHandler.java
    - 게시판 CRUD 각 기능을 `Command` 규칙에 따라 객체로 분리한다.
- App.java (App.java.01)
    - 명령어가 입력되면 `Command` 규칙에 따라 객체를 실행한다.
    - `/board2/xxx` 명령 처리는 삭제한다.

### 훈련 3: `Map`으로 `Command` 객체를 관리하라.

- App.java
    - 명령어를 `key`, `Command` 객체를 `value`로 하여 Map에 저장한다.
    - 각 명령에 대해 조건문으로 분기하는 부분을 간략하게 변경한다.
