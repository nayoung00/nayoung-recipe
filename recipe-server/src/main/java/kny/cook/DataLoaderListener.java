package kny.cook;

import java.util.Map;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.mariadb.BoardDaoImpl;
import kny.cook.dao.mariadb.MemberDaoImpl;
import kny.cook.dao.mariadb.PhotoBoardDaoImpl;
import kny.cook.dao.mariadb.PhotoFileDaoImpl;
import kny.cook.dao.mariadb.RecipeDaoImpl;
import kny.cook.sql.DataSource;
import kny.cook.sql.PlatformTransactionManager;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // DB 연결 정보
      String jdbcUrl = "jdbc:mariadb://localhost:3306/recipedb";
      String username = "study";
      String password = "1111";

      DataSource dataSource = new DataSource(jdbcUrl, username, password);

      context.put("dataSource", dataSource);

      context.put("boardDao", new BoardDaoImpl(dataSource));
      context.put("recipeDao", new RecipeDaoImpl(dataSource));
      context.put("memberDao", new MemberDaoImpl(dataSource));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(dataSource));
      context.put("photoFileDao", new PhotoFileDaoImpl(dataSource));

      PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
      context.put("transactionManager", txManager);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
  }
}
