# 27_2 - 리팩토링: 요청할 때 마다 프록시와 커맨드를 생성하는 부분 개선.

## 작업 소스 및 결과

- src/main/java/kny/cook/dao/proxy/XxxDaoProxy.java 변경
- src/main/java/kny/cook/ClientApp.java 변경


### 작업 1: 프록시 클래스 생성 부분을 변경하라.

- kny.cook.dao.proxy.XxxDaoProxy 변경한다.
  - 요청할 때 서버에 연결한다.
