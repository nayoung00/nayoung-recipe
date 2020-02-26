# 30_4 - Application Server 구조로 변경: 검색 기능 추가 


## 작업 소스 및 결과

- src/main/java/kny/cook/dao/MemberDao.java 변경
- src/main/java/kny/cook/dao/mariadb/MemberDaoImpl.java 변경
- src/main/java/kny/cook/servlet/MemberSearchServlet.java 추가
- src/main/java/kny/cook/ServerApp.java 변경


### 작업1: 회원 검색 기능을 추가하라.

서버에서 애플리케이션을 실행하는 방식의 이점은 "새 기능을 추가하더라도 
사용자 PC에 클라이언트 프로그램을 재설치 할 필요가 없다"는 것이다. 
검색 기능을 추가한 후 이를 확인한다.

- kny.cook.dao.MemberDao 변경
  - findByKeyword() 메서드 추가
- kny.cook.dao.mariadb.MemberDaoImpl 변경
  - findByKeyword() 메서드 구현
- kny.cook.servlet.MemberSearchServlet 추가
  - 클라이언트에게 검색 키워드를 요청한다.
  - 클라이언트가 보낸 키워드를 가지고 회원을 검색한다.
  - 검색한 결과를 가지고 출력 내용을 생성한다.
  - 클라이언트에게 보낸다.
- kny.cook.ServerApp 변경
  - '/member/search' 명령을 처리할 MemberSearchServlet 객체를 등록한다.
  