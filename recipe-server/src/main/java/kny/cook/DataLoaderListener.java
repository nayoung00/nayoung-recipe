package kny.cook;

import java.util.Map;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.mariadb.BoardDaoImpl;
import kny.cook.dao.mariadb.MemberDaoImpl;
import kny.cook.dao.mariadb.PhotoBoardDaoImpl;
import kny.cook.dao.mariadb.PhotoFileDaoImpl;
import kny.cook.dao.mariadb.RecipeDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {


  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // DB 연결 정
      String jdbcUrl = "jdbc:mariadb://localhost:3306/recipedb";
      String username = "study";
      String password = "1111";

      context.put("boardDao", new BoardDaoImpl(jdbcUrl, username, password));
      context.put("recipeDao", new RecipeDaoImpl(jdbcUrl, username, password));
      context.put("memberDao", new MemberDaoImpl(jdbcUrl, username, password));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(jdbcUrl, username, password));
      context.put("photoFileDao", new PhotoFileDaoImpl(jdbcUrl, username, password));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}
}
