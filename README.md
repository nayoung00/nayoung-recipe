# 26_8 - DAO 클래스의 공통점을 뽑아 수퍼 클래스로 정의(generalization 수행)

### 상속

- specialization
  - 수퍼 클래스를 상속 받아 특별한 기능을 수행하는 서브 클래스 만들기.
- generalization
  - 클래스들의 공통점을 뽑아 수퍼 클래스로 만든 후에 상속 관계를 맺기.
 

## 작업 소스 및 결과

- src/main/java/kny/cook/dao/AbstractObjectFileDao.java 추가
- src/main/java/kny/cook/dao/BoardObjectFileDao.java 변경
- src/main/java/kny/cook/dao/RecipeObjectFileDao.java 변경
- src/main/java/kny/cook/dao/MemberObjectFileDao.java 변경


### 작업 1: DAO의 공통점을 뽑아 수퍼 클래스로 정의하기.

- kny.cook.dao.AbstractObjectFileDao 클래스를 생성한다.

### 작업 2: BoardObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

- kny.cook.dao.BoardObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 작업 3: RecipeObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

- kny.cook.dao.RecipeObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.

### 작업 4: MemberObjectFileDao가 위에서 정의한 클래스를 상속 받도록 변경하기.

- kny.cook.dao.MemberObjectFileDao 변경한다.
  - 상속 받은 필드와 메서드를 사용한다.
  - indexOf()를 오버라이딩 한다.
