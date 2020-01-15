  # 17 - 인터페이스를 활용하여 객체 사용 규칙 정의하기
  
  ## 소스 및 결과
  
  - src/main/java/nayoung/cooknayoung/List.java 추가
  - src/main/java/nayoung/cooknayoung/ArrayList.java 변경
  - src/main/java/nayoung/cooknayoung/LinkedList.java 변경
  - src/main/java/nayoung/cooknayoung/handler/RecipeHandler.java 변경
  - src/main/java/nayoung/cooknayoung/handler/MemberHandler.java 변경
  - src/main/java/nayoung/cooknayoung/handler/BoardHandler.java 변경
  - src/main/java/nayoung/cooknayoung/lms/App.java 변경
  - src/main/java/nayoung/cooknayoung/util/AbstractList.java 변경(List.java의 이름 변경)
  - src/main/java/nayoung/cooknayoung/util/List.java 추가
  - src/main/java/nayoung/cooknayoung/AbstractList.java 변경
  
  ## 실습
  
  ### 훈련1. 데이터 목록을 다루는 ArrayList와 LinkedList의 공통점을 찾아 별도의 클래스로 분리하라.
  
  - List.java
      - ArrayList와 LinkedList의 공통 멤버를(필드와 메서드)를 선언한다.
      - 서브 클래스에서 반드시 재정의 해야 하는 메서드는 추상 메서드로 구현하지 않고 놓아 둔다. 
  - ArrayList.java
      - `AbstractList`를 상속 받는다.
      - 상속 받은 추상 메서드를 구현한다.
  - LinkedList.java
      - `AbstractList`를 상속 받는다.
      - 상속 받은 추상 메서드를 구현한다.
  - RecipeHandler.java
      - ArrayList 또는 LinkedList를 직접 지정하는 대신에 추상클래스를 필드로 선언한다.
      - 생성자에서 구체적으로 구현한 서브 클래스를 공급받도록 변경한다.
      - 따라서 특정 클래스(ArrayList나 LinkedList)에 의존하는 코드를 제거한다.
  - MemberHandler.java
      - ArrayList 또는 LinkedList를 직접 지정하는 대신에 추상클래스를 필드로 선언한다.
      - 생성자에서 구체적으로 구현한 서브 클래스를 공급받도록 변경한다.
      - 따라서 특정 클래스(ArrayList나 LinkedList)에 의존하는 코드를 제거한다.
  - BoardHandler.java
      - ArrayList 또는 LinkedList를 직접 지정하는 대신에 추상클래스를 필드로 선언한다.
      - 생성자에서 구체적으로 구현한 서브 클래스를 공급받도록 변경한다.
      - 따라서 특정 클래스(ArrayList나 LinkedList)에 의존하는 코드를 제거한다.
  - App.java
      - XxxHandler가 사용할 의존 객체(AbstractList 객체)를 준비한다.
      - XxxHandler를 생성할 때 해당 의존 객체를 넘겨준다.
      
      

### 훈련2. List.java에 추상 클래스와 추상 메서드 문법을 적용하라. 

  - AbstractList.java 
      - List 클래스의 이름을 AbstractList로 변경한다.
      - AbstractList 클래스를 추상 클래스로 선언한다.
      - AbstractList의 메서드를 추상 메서드로 정의한다.       
    
### 훈련3. 추상 클래스에서 추상 메서드를 추출하여 인터페이스를 정의하라.

  - List.java
      - AbstractList 추상 클래스에 있는 추상 메서드를 추출하여 따로 메서드 사용 규칙을 정의한다.
  - AbstractList.java
     - 추상 메서드를 List 인터페이스로 옮긴다.
     - List 규칙을 따른다.
