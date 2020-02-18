package kny.cook.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import kny.cook.dao.RecipeDao;

public class RecipeListCommand implements Command {

  RecipeDao recipeDao;

  public RecipeListCommand(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void execute() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");

      Connection con =
          DriverManager.getConnection("jdbc:mariadb://localhost:3306/recipedb", "study", "1111");

      Statement stmt = con.createStatement();

      ResultSet rs =
          stmt.executeQuery("select recipe_id, cook, material, met, expense, time from rms_recipe");

      while (rs.next()) {
        System.out.printf("%d, %s, %s, %s, %d, %d\n", rs.getInt("recipe_id"), rs.getString("cook"),
            rs.getString("material"), rs.getString("met"), rs.getInt("expense"), rs.getInt("time"));
      }
      rs.close();
      stmt.close();
      con.close();

    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}
