package kny.cook.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.util.ConnectionFactory;

public class RecipeDaoImpl implements RecipeDao {

 ConnectionFactory conFactory;

  public RecipeDaoImpl(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }

  @Override
  public int insert(Recipe recipe) throws Exception {

    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      con.setAutoCommit(true);

      int result = stmt.executeUpdate("insert into rms_recipe(cook,material,met,expense,time)"
          + " values('" + recipe.getCook() + "','" + recipe.getMaterial() + "','"
          + recipe.getMethod() + "', '" + recipe.getExpense() + "','" + recipe.getTime() + "')");

      return result;
    }
  }

  @Override
  public List<Recipe> findAll() throws Exception {
    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt
            .executeQuery("select recipe_id, cook, material, met, expense, time from rms_recipe")) {

      ArrayList<Recipe> list = new ArrayList<>();

      while (rs.next()) {
        Recipe recipe = new Recipe();
        recipe.setNo(rs.getInt("recipe_id"));
        recipe.setCook(rs.getString("cook"));
        recipe.setMaterial(rs.getString("material"));
        recipe.setMethod(rs.getString("met"));
        recipe.setExpense(rs.getInt("expense"));
        recipe.setTime(rs.getInt("time"));

        list.add(recipe);
      }
      return list;
    }
  }

  @Override
  public Recipe findByNo(int no) throws Exception {
    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery(
            "select recipe_id, cook, material, met, expense, time from rms_recipe where recipe_id="
                + no)) {

      if (rs.next()) {

        Recipe recipe = new Recipe();
        recipe.setNo(rs.getInt("recipe_id"));
        recipe.setCook(rs.getString("cook"));
        recipe.setMaterial(rs.getString("material"));
        recipe.setMethod(rs.getString("met"));
        recipe.setExpense(rs.getInt("expense"));
        recipe.setTime(rs.getInt("time"));

        return recipe;

      } else {
        return null;
      }
    }
  }

  @Override
  public int update(Recipe recipe) throws Exception {
    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate(
          "update rms_recipe set cook= '" + recipe.getCook() + "', material='" + recipe.getMethod()
              + "', met='" + recipe.getMethod() + "', expense='" + recipe.getExpense() + "', time='"
              + recipe.getTime() + "' where recipe_id=" + recipe.getNo());

      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = conFactory.getConnection();
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("delete from rms_recipe where recipe_id = " + no);
      return result;
    }
  }

}
