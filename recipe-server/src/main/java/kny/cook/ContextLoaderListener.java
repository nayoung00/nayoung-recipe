package kny.cook;

import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import kny.cook.context.ApplicationContextListener;
import kny.cook.util.RequestHandler;
import kny.cook.util.RequestMapping;
import kny.cook.util.RequestMappingHandlerMapping;

public class ContextLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      // Spring IoC 컨테이너 준비
      ApplicationContext appCtx = new AnnotationConfigApplicationContext(//
          AppConfig.class // Spring IoC 컨테이너의 설정 정보를 담고 있는 클래스 타입을 지정.
      );
      printBeans(appCtx);

      context.put("iocContainer", appCtx);

      System.out.println("-----------------------------------");

      // @Component 애노테이션이 붙은 객체를 찾는다.
      RequestMappingHandlerMapping handlerMapper = new RequestMappingHandlerMapping();
      String[] beanNames = appCtx.getBeanNamesForAnnotation(Component.class);
      for (String beanName : beanNames) {
        Object component = appCtx.getBean(beanName);

        // @RequestHandler가 붙은 메서드를 찾는다.
        Method method = getRequestHandler(component.getClass());
        if (method != null) {
          // 클라이언트 명령을 처리하는 메서드 정보를 준비한다.
          RequestHandler requestHandler = new RequestHandler(method, component);

          handlerMapper.addHandler(requestHandler.getPath(), requestHandler);
        }
      }
      context.put("handlerMapper", handlerMapper);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void printBeans(ApplicationContext appCtx) {
    System.out.println("Spring IoC 컨테이너에 들어있는 객체들: ");

    String[] beanNames = appCtx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      System.out.printf("%s ======> %s\n", beanName, appCtx.getBean(beanName).getClass().getName());
    }
  }

  private Method getRequestHandler(Class<?> type) {

    Method[] methods = type.getMethods();
    for (Method m : methods) {
      // 메서드에 @RequestMapping 애노테이션이 붙었는지 검사한다.
      RequestMapping anno = m.getAnnotation(RequestMapping.class);
      if (anno != null) {
        return m;
      }
    }
    return null;
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

  }
}
