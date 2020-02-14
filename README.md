# 27_3 - 리팩토링: 서버 연결 부분을 캡슐화

## 작업 소스 및 결과

- src/main/java/kny/cook/dao/proxy/Worker.java 추가
- src/main/java/kny/cook/dao/proxy/DaoProxyHelper.java 추가
- src/main/java/kny/cook/dao/proxy/XxxDaoProxy.java 변경
- src/main/java/kny/cook/ClientApp.java 변경

### 작업 1: 서버와 통신을 담당할 실제 작업의 규칙을 정의하라.

- kny.cook.dao.proxy.Worker 인터페이스를 정의한다.

### 작업 2: DaoProxy를 도와 서버와의 연결을 담당할 객체를 정의하라. 

- kny.cook.dao.proxy.DaoProxyHelper 를 정의한다.

### 작업 3: DaoProxyHelper를 사용하도록 DaoProxy를 변경하라.

- kny.cook.dao.proxy.XxxDaoProxy 를 변경한다.

### 작업 4: DaoProxyHelper가 추가된 것에 맞춰 ClientApp을 변경하라.

- kny.cook.ClientApp 변경한다.
  