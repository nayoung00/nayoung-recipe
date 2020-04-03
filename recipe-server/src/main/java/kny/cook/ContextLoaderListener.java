package kny.cook;

import java.io.InputStream;
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
      appCtx.printBeans();

      context.put("iocContainer", appCtx);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
