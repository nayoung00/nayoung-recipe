package kny.cook;

import org.springframework.context.annotation.ComponentScan;

// Spring IoC 컨테이너가 탐색할 패키지 설정
// => 지정한 패키지 및 하위 패키지를 모두 뒤져서
// @Component 애노테이션이 붙은 클래스를 찾아 객체를 생성한다.
//
@ComponentScan(value = "kny.cook")
public class AppConfig {

  public AppConfig() {
    System.out.println("AppConfig 객체 생성!");
  }

}
