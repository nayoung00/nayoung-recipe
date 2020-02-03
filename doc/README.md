v25_2 - 애플리케이션 시작/종료시 데이터를 로딩하고 저장할 옵저버 추가

## 소스 및 결과

- src/main/java/kny/cook/DataLoaderListener.java 추가
- src/main/java/kny/cook/App.java 변경


### 작업 1: 애플리케이션을 시작하거나 종료할 때 작업을 수행할 옵저버를 만든다.

- DataLoaderListener.java 추가
  - ApplicationContextListener를 구현한다.
  - 테스트 할 용도로 간단하게 구현한다.
  

### 작업 2: DataLoaderListener 옵저버를 App 객체에 등록한 후 실행되는 걸 확인한다.

- App.java 변경 (App.java.01)
  - DataLoaderListener 객체를 생성한 후 App 객체에 등록한다.
  - 실행하여 옵저버가 동작하는 지를 확인한다.
    

### 훈련 3: DataLoaderListener 옵저버에서 데이터를 로딩하고 저장하게 한다..

- DataLoaderListener.java 변경
  - App 클래스에 있는 List 객체를 이 클래스로 옮긴다.
  - App 클래스에 있는 데이터 로딩, 저장 관련 메서드를 이 클래스로 옮긴다.
  
- App.java 변경 (계속 작업 중)
  - List 객체를 필드에서 제거한다.(DataLoaderListener가 할 일이다.)
  - 데이터 로딩, 저장 관련 메서드를 제거한다.(DataLoaderListener가 할 일이다.)
  - 데이터 로딩 호출 코드를 제거한다.(DataLoaderListener가 할 일이다.)
  - 데이터 저장 호출 코드를 제거한다.(DataLoaderListener가 할 일이다.)

### 훈련 4: App 클래스가 옵저버의 결과물을 사용할 수 있게 한다.

ApplicationContextListener.java (변경)
  - contextInitialized()에 Map 파라미터를 추가한다.
  - contextDestroyed()에 Map 파라미터를 추가한다.

### 훈련 5: DataLoaderListener의 작업 결과를 Map 객체를 통해 공유한다.

DataLoaderListener.java (변경)
  - 데이터 로딩 결과를 Map 객체에 보관한다.

### 훈련 6: DataLoaderListener에서 준비한 List 객체를 Command에게 전달한다.

App.java (변경)
  - 애플리케이션이 시작될 때 옵저버를 실행한 후 Map 에 저장된 작업 결과를 꺼내
    Command 객체에 전달한다.
    


