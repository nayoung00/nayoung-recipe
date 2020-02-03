# 25_3 - 애플리케이션을 시작/종료시 안내 문구 출력.


## 소스 및 결과

- src/main/java/kny/cook/GreetingListener.java 추가
- src/main/java/kny/cook/App.java 변경


### 작업 1: 애플리케이션을 시작하거나 종료할 때 안내 문구를 출력할 옵저버를 만들라.

- GreetingListener.java 추가 
  - ApplicationContextListener를 구현한다.
  - 안내 문구를 출력한다.
  

### 작업 2: DataLoaderListener 옵저버를 App 객체에 등록한 후 실행되는걸 확인하라.

- App.java 변경 (App.java.01)
  - GreetingListener 객체를 생성한 후 App 객체에 등록한다.
  - 실행하여 옵저버가 동작하는 지를 확인한다.