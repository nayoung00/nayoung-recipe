package kny.cook;

import java.util.Map;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.mariadb.BoardDaoImpl;
import kny.cook.dao.mariadb.MemberDaoImpl;
import kny.cook.dao.mariadb.PhotoBoardDaoImpl;
import kny.cook.dao.mariadb.PhotoFileDaoImpl;
import kny.cook.dao.mariadb.RecipeDaoImpl;
import kny.cook.sql.PlatformTransactionManager;
import kny.cook.util.ConnectionFactory;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // DB 연결 정보
      String jdbcUrl = "jdbc:mariadb://localhost:3306/recipedb";
      String username = "study";
      String password = "1111";

      ConnectionFactory conFactory = new ConnectionFactory(jdbcUrl, username, password);

      context.put("connectionFactory", conFactory);

      context.put("boardDao", new BoardDaoImpl(conFactory));
      context.put("recipeDao", new RecipeDaoImpl(conFactory));
      context.put("memberDao", new MemberDaoImpl(conFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(conFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(conFactory));

      PlatformTransactionManager txManager = new PlatformTransactionManager(conFactory);
      context.put("transactionManager", txManager);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
