package kny.cook;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.BoardDao;
import kny.cook.dao.MemberDao;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.PhotoFileDao;
import kny.cook.dao.RecipeDao;
import kny.cook.sql.MybatisDaoFactory;
import kny.cook.sql.PlatformTransactionManager;
import kny.cook.sql.SqlSessionFactoryProxy;
import kny.cook.util.ApplicationContext;
import kny.cook.util.Component;
import kny.cook.util.RequestHandler;
import kny.cook.util.RequestMapping;
import kny.cook.util.RequestMappingHandlerMapping;

public class ContextLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {

    try {
      // ApplicationContext에서 자동으로 생성하지 못하는 객체는
      // 따로 생성하여 Map에 보관한다.
      HashMap<String, Object> beans = new HashMap<>();

      // Mybatis 설정 파일을 로딩할 때 사용할 입력 스트림 준비
      InputStream inputStream = Resources.getResourceAsStream("kny/cook/conf/mybatis-config.xml");


      SqlSessionFactory sqlSessionFactory =
          new SqlSessionFactoryProxy(new SqlSessionFactoryBuilder().build(inputStream));
      beans.put("SqlSessionFactory", sqlSessionFactory);

      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      beans.put("recipeDao", daoFactory.createDao(RecipeDao.class));
      beans.put("boardDao", daoFactory.createDao(BoardDao.class));
      beans.put("memberDao", daoFactory.createDao(MemberDao.class));
      beans.put("photoBoardDao", daoFactory.createDao(PhotoBoardDao.class));
      beans.put("photoFileDao", daoFactory.createDao(PhotoFileDao.class));

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);
      beans.put("transactionManager", txManager);

      // IoC 컨테이너 준비
      ApplicationContext appCtx = new ApplicationContext("kny.cook", beans);

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
