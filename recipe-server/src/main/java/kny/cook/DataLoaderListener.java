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
import kny.cook.service.impl.BoardServiceImpl2;
import kny.cook.service.impl.MemberServiceImpl;
import kny.cook.service.impl.PhotoBoardServiceImpl;
import kny.cook.service.impl.RecipeServiceImpl;
import kny.cook.sql.MybatisDaoFactory;
import kny.cook.sql.PlatformTransactionManager;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // Mybatis 객체 준비
      InputStream inputStream = Resources.getResourceAsStream("kny/cook/conf/mybatis-config.xml");

      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      context.put("SqlSessionFactory", sqlSessionFactory);

      MybatisDaoFactory daoFactory = new MybatisDaoFactory(sqlSessionFactory);

      PhotoFileDao photoFileDao = daoFactory.createDao(PhotoFileDao.class);
      BoardDao boardDao = daoFactory.createDao(BoardDao.class);
      MemberDao memberDao = daoFactory.createDao(MemberDao.class);
      RecipeDao recipeDao = daoFactory.createDao(RecipeDao.class);
      PhotoBoardDao photoBoardDao = daoFactory.createDao(PhotoBoardDao.class);

      PlatformTransactionManager txManager = new PlatformTransactionManager(sqlSessionFactory);

      context.put("boardService", new BoardServiceImpl2(sqlSessionFactory));
      context.put("recipeService", new RecipeServiceImpl(recipeDao));
      context.put("memberService", new MemberServiceImpl(memberDao));
      context.put("photoBoardService",
          new PhotoBoardServiceImpl(txManager, photoBoardDao, photoFileDao));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
