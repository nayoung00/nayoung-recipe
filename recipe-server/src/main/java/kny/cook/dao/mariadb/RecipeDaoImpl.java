package kny.cook.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.sql.DataSource;

public class RecipeDaoImpl implements RecipeDao {

  DataSource dataSource;

  public RecipeDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(Recipe recipe) throws Exception {

    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "insert into rms_recipe(cook,material,met,expense,time) values(?,?,?,?,?)")) {

      stmt.setString(1, recipe.getCook());
      stmt.setString(1, recipe.getMaterial());
      stmt.setString(1, recipe.getMethod());
      stmt.setInt(1, recipe.getExpense());
      stmt.setInt(1, recipe.getTime());

      return stmt.executeUpdate();
    }

  }

  @Override
  public List<Recipe> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select recipe_id, cook, material, met, expense, time from rms_recipe")) {
      try (ResultSet rs = stmt.executeQuery()) {

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
  }

  @Override
  public Recipe findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select recipe_id, cook, material, met, expense, time from rms_recipe where recipe_id=?")) {
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {

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
  }

  @Override
  public int update(Recipe recipe) throws Exception {
    try (Connection con = dataSource.getConnection();

        PreparedStatement stmt = con.prepareStatement(
            "update rms_recipe set cook= ?, material=?, met=?, expense=?, time=? where recipe_id=?")) {

      stmt.setString(1, recipe.getCook());
      stmt.setString(1, recipe.getMaterial());
      stmt.setString(1, recipe.getMethod());
      stmt.setInt(1, recipe.getExpense());
      stmt.setInt(1, recipe.getTime());
      stmt.setInt(1, recipe.getNo());

      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("delete from rms_recipe where recipe_id =?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }
}
