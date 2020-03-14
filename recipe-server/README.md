# 35_1 - MyBatis SQL 맵퍼 프레임워크를 사용하여 JDBC 코드를 대체하기

## 작업 소스 및 결과

- build.gradle 변경
- src/main/java/kny/cook/domain/PhotoBoard.java 변경
- src/main/java/kny/cook/dao/mariadb/BoardDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/LessonDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/PhotoBoardDaoImpl.java 변경
- src/main/java/kny/cook/dao/mariadb/PhotoFileDaoImpl.java 변경
- src/main/java/kny/cook/servlet/PhotoBoardDetailServlet.java 변경
- src/main/java/kny/cook/DataLoaderListener.java 변경
- src/main/java/kny/cook/ServerApp.java 변경
- src/main/resources/kny/cook/conf/mybatis-config.xml 추가
- src/main/resources/kny/cook/conf/jdbc.properties 추가
- src/main/resources/kny/cook/mapper/BoardMapper.xml 추가
- src/main/resources/kny/cook/mapper/LessonMapper.xml 추가
- src/main/resources/kny/cook/mapper/MemberMapper.xml 추가
- src/main/resources/kny/cook/mapper/PhotoBoardMapper.xml 추가
- src/main/resources/kny/cook/mapper/PhotoFileMapper.xml 추가


### 작업1: 프로젝트에 MyBatis 라이브러리를 추가한다.

- build.gradle   
  - 의존 라이브러리 블록에서 `mybatis` 라이브러리를 등록한다.
- gradle을 이용하여 eclipse 설정 파일을 갱신한다.
  - `$ gradle eclipse`
- 이클립스에서 프로젝트를 갱신한다.
  
### 작업2: `MyBatis` 설정 파일을 준비한다.

- kny/cook/conf/jdbc.properties
    - `MyBatis` 설정 파일에서 참고할 DBMS 접속 정보를 등록한다.
- kny/cook/conf/mybatis-config.xml
    - `MyBatis` 설정 파일이다.
    - DBMS 서버의 접속 정보를 갖고 있는 jdbc.properties 파일의 경로를 등록한다.
    - DBMS 서버 정보를 설정한다.
    - DB 커넥션 풀을 설정한다.

### 작업3: BoardDaoImpl 에 Mybatis를 적용한다.

- kny.cook.dao.mariadb.BoardDaoImpl 클래스 변경
  - SQL을 뜯어내어 BoardMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- kny/cook/mapper/BoardMapper.xml 추가
  - BoardDaoImpl 에 있던 SQL문을 이 파일로 옮긴다.
- kny/cook/conf/mybatis-config.xml 변경 
  - BoardMapper 파일의 경로를 등록한다.
- kny.cook.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비한다.
  - BoardDaoImpl 에 주입한다.

### 작업4: MemberDaoImpl 에 Mybatis를 적용한다.

- kny.cook.dao.mariadb.MemberDaoImpl 클래스 변경
  - SQL을 뜯어내어 MemberMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- kny/cook/mapper/MemberMapper.xml 추가
  - MemberDaoImpl 에 있던 SQL문을 이 파일로 옮긴다.
- kny/cook/conf/mybatis-config.xml 변경 
  - MemberMapper 파일의 경로를 등록한다.
- kny.cook.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비한다.
  - MemberDaoImpl 에 주입한다.

### 작업5: LessonDaoImpl 에 Mybatis를 적용한다.

- kny.cook.dao.mariadb.LessonDaoImpl 클래스 변경
  - SQL을 뜯어내어 LessonMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- kny/cook/mapper/LessonMapper.xml 추가
  - LessonDaoImpl 에 있던 SQL문을 이 파일로 옮긴다.
- kny/cook/conf/mybatis-config.xml 변경 
  - LessonMapper 파일의 경로를 등록한다.
- kny.cook.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비한다.
  - LessonDaoImpl 에 주입한다.

### 작업6: PhotoBoardDaoImpl 에 Mybatis를 적용한다.

- kny.cook.domain.PhotoBoard 클래스 변경
  - PhotoFile 목록 필드를 추가한다.
- kny.cook.dao.mariadb.PhotoBoardDaoImpl 클래스 변경
  - SQL을 뜯어내어 PhotoBoardMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- kny/cook/mapper/PhotoBoardMapper.xml 추가
  - PhotoBoardDaoImpl 에 있던 SQL문을 이 파일로 옮긴다.
- kny/cook/conf/mybatis-config.xml 변경 
  - PhotoBoardMapper 파일의 경로를 등록한다.
- kny.cook.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비한다.
  - PhotoBoardDaoImpl 에 주입한다.
- kny.cook.servlet.PhotoBoardDetailServlet 변경
  - PhotoFileDao 주입을 제거한다.
  - PHotoBoardDao로 첨부파일까지 모두 가져온다.
- kny.cook.ServerApp 변경
  - PhotoBoardDetailServlet에 PhotoFileDao 주입을 제거한다.
  
### 작업7: PhotoFileDaoImpl 에 Mybatis를 적용한다.

- kny.cook.dao.mariadb.PhotoFileDaoImpl 클래스 변경
  - SQL을 뜯어내어 PhotoFileMapper.xml로 옮긴다.
  - JDBC 코드를 뜯어내고 그 자리에 Mybatis 클래스로 대체한다.
- kny/cook/mapper/PhotoFileMapper.xml 추가
  - PhotoFileDaoImpl 에 있던 SQL문을 이 파일로 옮긴다.
- kny/cook/conf/mybatis-config.xml 변경 
  - PhotoFileMapper 파일의 경로를 등록한다.
- kny.cook.DataLoaderListener 변경
  - SqlSessionFactory 객체를 준비한다.
  - PhotoFileDaoImpl 에 주입한다.


- 사진 게시글을 등록한다.
- 사진 파일을 등록할 때, 오류가 발생하도록 긴 파일명을 입력한다.
- 오류가 발생한 후에 사진 게시글이 등록되었는지 취소되었는지 확인한다.
- 취소되지 않은 이유는 Mybatis의 SQLSession이 자체적으로 커넥션을 관리하기 때문이다.
 