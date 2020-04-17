package kny.cook;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 서블릿 컨테이너가 시작하거나 종료할 때
// 이 클래스의 객체에 대해 메서드를 호출한다.
// 즉 이 클래스는 서블릿 컨테이너의 시작과 종료에 대해 알림을 받는다.
// 조건:
// => javax.servlet.ServletContextListener 인터페이스를 구현해야 한다.
//
@WebListener
public class ContextLoaderListener implements ServletContextListener {

  static Logger logger = LogManager.getLogger(ContextLoaderListener.class);

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    // 서블릿 컨테이너가 실행할 때
    // 실행한 사실을 알리기 위하여 모든 ServletContextListener에 대해
    // 이 메서드를 호출한다.

    // 준비한 객체를 담을 공용 바구니를 준비한다.
    ServletContext servletContext = sce.getServletContext();
    // => 이 객체는 웹애플리케이션 설정 정보를 제공한다.
    // => 또한 서블릿이 공유할 객체를 담는 바구니 역할도 겸한다.

    try {
      // Spring IoC 컨테이너 준비
      ApplicationContext iocContainer = new AnnotationConfigApplicationContext(//
          AppConfig.class // Spring IoC 컨테이너의 설정 정보를 담고 있는 클래스 타입을 지정.
      );
      printBeans(iocContainer);

      servletContext.setAttribute("iocContainer", iocContainer);

      logger.debug("-----------------------------------");

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void printBeans(ApplicationContext appCtx) {
    logger.debug("Spring IoC 컨테이너에 들어있는 객체들: ");
    String[] beanNames = appCtx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      logger.debug(
          String.format("%s ======> %s", beanName, appCtx.getBean(beanName).getClass().getName()));
    }
  }


  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // 서블릿 컨테이너가 종료되기 직전에 호출된다.
    // 주로 서블릿이 사용한 자원을 해제시키는 코드를 둔다.
  }
}
