# 30_5 - Application Server 구조로 변경: 서버 종료 명령 처리하기

## 작업 소스 및 결과

- src/main/java/kny/cook/ClientApp.java 변경


### 작업1: '/server/stop' 명령을 처리하라.

- kny.cook.ClientApp 변경
  - 사용자가 '/server/stop'을 입력했을 때 서버가 종료 작업을 즉시 할 수 있도록 두 번 요청한다.

