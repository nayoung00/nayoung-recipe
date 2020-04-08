# 42_1 - 애노테이션을 이용하여 트랜잭션 제어하기

## 작업 소스 및 결과

- src/main/java/kny/cook/DatabaseConfig.java 변경
- src/main/java/kny/cook/service/impl/PhotoBoardServiceImpl.java 변경


### 작업1: 애노테이션으로 트랜잭션을 제어할 수 있도록 해당 기능을 활성화시킨다.

- kny.cook.DatabaseConfig 변경
  - @Transactional 애노테이션 기능을 활성화시키기 위해서 
    @EnableTransactionManagement 애노테이션을 추가로 선언한다.

#### @EnableTransactionManagement 

메서드에 @Transactional 이 붙어 있으면 
해당 메서드를 트랜잭션으로 묶기 위해  
프록시 객체를 자동 생성한다.
  

### 작업2: @Transactional 애노테이션으로 트랜잭션을 제어하라.

- kny.cook.service.impl.PhotoBoardServiceImpl 변경
  - TransactionTemplate을 사용하는 대신에 @Transactional 애노테이션을 붙인다.
  - add(), update(), delete()을 변경한다.
  
