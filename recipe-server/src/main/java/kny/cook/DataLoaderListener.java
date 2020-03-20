package kny.cook;

import java.io.InputStream;
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
import kny.cook.dao.mariadb.BoardDaoImpl;
import kny.cook.dao.mariadb.MemberDaoImpl;
import kny.cook.dao.mariadb.PhotoBoardDaoImpl;
import kny.cook.dao.mariadb.PhotoFileDaoImpl;
import kny.cook.dao.mariadb.RecipeDaoImpl;
import kny.cook.service.impl.BoardServiceImpl;
import kny.cook.service.impl.MemberServiceImpl;
import kny.cook.service.impl.PhotoBoardServiceImpl;
import kny.cook.service.impl.RecipeServiceImpl;
import kny.cook.sql.PlatformTransactionManager;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // Mybatis 객체 준비
      InputStream inputStream = Resources.getResourceAsStream("kny/cook/conf/mybatis-config.xml");

      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      context.put("SqlSessionFactory", sqlSessionFactory);

      PhotoFileDao photoFileDao = new PhotoFileDaoImpl(sqlSessionFactory);
      BoardDao boardDao = new BoardDaoImpl(sqlSessionFactory);
      MemberDao memberDao = new MemberDaoImpl(sqlSessionFactory);
      RecipeDao recipeDao = new RecipeDaoImpl(sqlSessionFactory);
      PhotoBoardDao photoBoardDao = new PhotoBoardDaoImpl(sqlSessionFactory);

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);

      context.put("boardSerivce", new BoardServiceImpl(boardDao));
      context.put("recipeService", new RecipeServiceImpl(recipeDao));
      context.put("memberService", new MemberServiceImpl(memberDao));
      context.put("photoBoardService",
          new PhotoBoardServiceImpl(txManager, photoBoardDao, photoFileDao));
      context.put("photoFileDao", new PhotoFileDaoImpl(sqlSessionFactory));
      context.put("transactionManager", txManager);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
