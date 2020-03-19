# 35_3 - MyBatis의 dynamic sql 문법 사용하기


## 작업 소스 및 결과

- src/main/java/kny/cook/util/Prompt.java 변경
- src/main/java/kny/cook/ServerApp.java 변경
- src/main/java/kny/cook/mapper/XxxDaoImpl.java 변경
- src/main/java/kny/cook/dao/PhotoFileDao.java 변경
- src/main/java/kny/cook/dao/RecipeDao.java 변경
- src/main/java/kny/cook/dao/mariadb/RecipeDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/PhotoFileDaoImpl.java 변경
- src/main/java/kny/cook/servlet/PhotoBoardAddServlet.java 변경
- src/main/java/kny/cook/servlet/PhotoBoardUpdateServlet.java 변경
- src/main/java/kny/cook/servlet/MemberUpdateServlet.java 변경
- src/main/java/kny/cook/servlet/RecipeUpdateServlet.java 변경
- src/main/java/kny/cook/servlet/RecipeSearchServlet.java 변경


### 작업1: `sql` 태그를 사용하여 공통 SQL 코드를 분리한다.

- src/main/resources/kny/cook/mapper/*Mapper.xml
  
### 작업2: `foreach` 태그를를 사용하여 insert 문 생성하기

사진 게시글의 첨부파일을 입력할 때, 여러 값들을 한 번에 입력하기 

- kny.cook.servlet.PhotoBoardAddServlet 변경
  - 파일 목록을 한 번에 insert 하기
- kny.cook.servlet.PhotoBoardUpdateServlet 변경
  - 파일 목록을 한 번에 insert 하기  
- kny.cook.dao.PhotoFileDao 변경
  - insert(PhotoFile) 메서드를 insert(PhotoBoard) 로 변경한다.
- kny.cook.dao.mariadb.PhotoFileDaoImpl 변경
  - insert()를 변경한다.
- src/main/resources/kny/cook/mapper/PhotoFileMapper.xml 변경
  - insertPhotoFile SQL 변경한다.
  - `foreach` 태그를 적용하여 여러 개의 값을 한 번에 insert 한다.
  
### 작업3: `set` 태그를 사용하여 update할 때 일부 컬럼만 변경한다.

데이터를 변경할 때 일부 컬럼의 값만 변경하기

- src/main/resources/kny/cook/mapper/RecipeMapper.xml 변경
  - updateRecipe SQL을 변경한다.
- kny.cook.util.Prompt 변경
  - 클라이언트가 보낸 값을 지정된 타입으로 변경할 수 없을 때의 예외를 처리한다.
- kny.cook.servlet.RecipeUpdateServlet 변경
  - 클라이언트가 값을 보내지 않은 항목은 빈문자열이나 null, 0으로 설정한다.
  - 이 경우 update 대상 컬럼에서 제외된다.
- src/main/resources/kny/cook/mapper/MemberMapper.xml 변경
  - updateMember SQL을 변경한다.
- kny.cook.servlet.MemberUpdateServlet 변경
  - 클라이언트가 값을 보내지 않은 항목은 빈문자열이나 null, 0으로 설정한다.
  - 이 경우 update 대상 컬럼에서 제외된다.
  
### 작업4: `where` 태그를 사용하여 검색 조건을 변경한다. 

레시피를 검색(요리명, 재료, 방법, 비용, 조리시간)하는 기능을 추가한다.
검색 조건은 AND 연산으로 처리한다.

- src/main/resources/kny/cook/mapper/RecipeMapper.xml 변경
  - selectRecipe SQL문을 변경한다.
  - `where` 태그를 적용하여 조건을 만족하는 데이터를 찾는다. 
- kny.cook.dao.RecipeDao 변경
  - findByKeyword() 메서드를 추가한다.
- kny.cook.servlet.RecipeSearchServlet 추가 
  - 검색 요청을 처리한다.
- kny.cook.ServerApp 변경
  - RecipeSearchServlet 객체 등록 
