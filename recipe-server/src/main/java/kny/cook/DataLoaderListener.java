package kny.cook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.mariadb.BoardDaoImpl;
import kny.cook.dao.mariadb.MemberDaoImpl;
import kny.cook.dao.mariadb.RecipeDaoImpl;

public class DataLoaderListener implements ApplicationContextListener {

  Connection con;

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // DB 연결 객체 준비
      Class.forName("org.mariadb.jdbc.Driver");
      con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/recipedb", "study", "1111");

      context.put("boardDao", new BoardDaoImpl(con));
      context.put("recipeDao", new RecipeDaoImpl(con));
      context.put("memberDao", new MemberDaoImpl(con));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    try {
      con.close();
    } catch (Exception e) {

    }
  }
}
