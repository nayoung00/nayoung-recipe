# 28 - 데이터 관리를 전문 프로그램인 DBMS에게 맡기기

## 작업 소스 및 결과

- src/main/java/kny/cook/handler/BoardListCommand.java 변경
- src/main/java/kny/cook/handler/RecipeListCommand.java 변경
- src/main/java/kny/cook/handler/MemberListCommand.java 변경

### 작업 1: 프로젝트에 `MariaDB` JDBC 드라이버를 추가하라.

- build.gradle
    - 의존 라이브러리에 MariaDB JDBC Driver 정보를 추가한다.
