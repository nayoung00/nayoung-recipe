package kny.cook;

import java.util.Map;
import kny.cook.context.ApplicationContextListener;

public class GreetingListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("[요리 레시피]에 오신걸 환영합니다!");
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    System.out.println("안녕히가세요!");
  }

}
