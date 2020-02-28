# 31_1 - 트랜잭션 적용 전: 사진 게시판 추가하기

## 작업 소스 및 결과

- src/main/java/kny/cook/domain/PhotoBoard.java 추가
- src/main/java/kny/cook/dao/PhotoBoardDao.java 추가
- src/main/java/kny/cook/dao/mariadb/PhotoBoardDaoImpl.java 추가
- src/main/java/kny/cook/servlet/PhotoBoardListServlet.java 추가
- src/main/java/kny/cook/servlet/PhotoBoardDetailServlet.java 추가
- src/main/java/kny/cook/servlet/PhotoBoardAddServlet.java 추가
- src/main/java/kny/cook/servlet/PhotoBoardUpdateServlet.java 추가
- src/main/java/kny/cook/servlet/PhotoBoardDeleteServlet.java 추가
- src/main/java/kny/cook/DataLoaderListener.java 변경
- src/main/java/kny/cook/ServerApp.java 변경

## 실습  

### 훈련1: `레시피 사진 게시판` 데이터를 다룰 DAO를 생성하라.

- kny.cook.domain.PhotoBoard 추가
  - 사진 게시물의 데이터 타입을 정의한다.
- kny.cook.dao.PhotoBoardDao 인터페이스 추가
  - 사진 게시물의 CRUD 관련 메서드 호출 규칙을 정의한다.
- kny.cook.dao.mariadb.PhotoBoardDaoImpl 추가
  - PhotoBoardDao 인터페이스를 구현한다.
- kny.cook.DataLoaderListener 변경
  - PhotoBoardDao 객체를 생성한다.

### 훈련1: '/photoboard/list' 명령을 처리하라.

- kny.cook.servlet.PhotoBoardListServlet 추가
    - 사진 게시물의 목록을 출력한다.
- kny.cook.ServerApp 변경
    - `PhotoBoardListServlet` 객체를 생성하여 커맨드 맵에 보관한다.

`ClientApp` 실행 예:
```
명령> /photoboard/list
레시피번호? 1
레시피명: xxxxx
----------------------------------------------------
  1, 레시피 오리엔테이션           , 2018-11-14, 0
  2, 1차 과제 발표            , 2018-11-14, 0
  3, null                , 2018-11-14, 0
  4, 과제 발표회              , 2018-11-14, 0
```
    
### 훈련2: '/photoboard/detail' 명령을 처리하라.

- kny.cook.domain.PhotoBoard 변경
  - 레시피 정보를 담을 Recipe 타입의 인스턴스 필드를 추가한다.
- kny.cook.dao.mariadb.PhotoBoardDaoImp 변경
  - findByNo(사진게시글번호) 메서드 변경한다.
  - 사진 게시글의 상세정보를 가져올 때 rms_photo와 rms_recipe을 조인한다.
  - rms_photo 데이터는 PhotoBoard에 저장하고, rms_recipe 데이터는 Recipe에 저장한다. 
- kny.cook.servlet.PhotoBoardDetailServlet 추가
    - 사진 게시물의 상세정보를 출력한다.
- kny.cook.ServerApp 변경
    - `PhotoBoardDetailServlet` 객체를 생성하여 커맨드 맵에 보관한다.

`ClientApp` 실행 예:
```
명령> /photoboard/detail
번호?
6
제목: test1
작성일: 2018-11-14
조회수: 0
레시피명: xxxx

명령> /photoboard/detail
번호?
5
해당 번호의 사진 게시글이 없습니다.
```

### 훈련3: '/photoboard/add' 명령을 처리하라.

- kny.cook.servlet.PhotoBoardAddServlet 추가
    - 사진 게시물을 입력한다.
- kny.cook.ServerApp 변경
    - `PhotoBoardAddServlet` 객체를 생성하여 커맨드 맵에 보관한다.

`ClientApp` 실행 예:
```
명령> /photoboard/add
제목?
test1
레시피 번호?
2
사진을 저장했습니다.

명령> /photoboard/add
제목?
test1
레시피 번호?
200
레시피 번호가 유효하지 않습니다.
```

### 훈련4: '/photoboard/update' 명령을 처리하라.

- kny.cook.servlet.PhotoBoardUpdateServlet 추가
    - 사진 게시물을 변경한다. 
- kny.cook.ServerApp 변경
    - `PhotoBoardUpdateServlet` 객체를 생성하여 커맨드 맵에 보관한다.

`ClientApp` 실행 예:
```
명령> /photoboard/update
번호?
6
제목(test1)?
test1...xx
사진을 변경했습니다.

명령> /photoboard/update
번호?
600
해당 번호의 사진 게시글이 없습니다.
```

### 훈련5: '/photoboard/delete' 명령을 처리하라.

- kny.cook.servlet.PhotoBoardDeleteServlet 추가
    - 사진 게시물을 삭제한다. 
- kny.cook.ServerApp 변경
    - `PhotoBoardDeleteServlet` 객체를 생성하여 커맨드 맵에 보관한다.

`ClientApp` 실행 예:
```
명령> /photoboard/delete
번호?
6
사진 게시글을 삭제했습니다.

명령> /photoboard/delete
번호?
600
해당 번호의 사진 게시글이 없습니다.
```

