package kny.cook.dao.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.Recipe;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  String jdbcUrl;
  String username;
  String password;

  public PhotoBoardDaoImpl(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
    
  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {

      int result = stmt.executeUpdate("insert into rms_photo(titl,recipe_id) values('"
          + photoBoard.getTitle() + "', " + photoBoard.getRecipe().getNo() + ")",
          Statement.RETURN_GENERATED_KEYS);


      try (ResultSet generatedKeySet = stmt.getGeneratedKeys()) {
        generatedKeySet.next();

        photoBoard.setNo(generatedKeySet.getInt(1));
      }
      return result;
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery( //
            "select" //
                + " p.photo_id," //
                + " p.titl," //
                + " p.cdt," //
                + " p.vw_cnt,"//
                + " r.recipe_id,"//
                + " r.cook" //
                + " from rms_photo p" //
                + " inner join rms_recipe r on p.recipe_id=r.recipe_id" //
                + " where photo_id=" + no)) {

      if (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreatedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        Recipe recipe = new Recipe();
        recipe.setNo(rs.getInt("recipe_id"));
        recipe.setCook(rs.getString("cook"));

        photoBoard.setRecipe(recipe);

        return photoBoard;

      } else {
        return null;
      }
    }

  }

  @Override
  public int update(PhotoBoard photoboard) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate( //
          "update rms_photo set titl='" //
              + photoboard.getTitle() //
              + "' where photo_id=" + photoboard.getNo());
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement()) {
      int result = stmt.executeUpdate( //
          "delete from rms_photo" //
              + " where photo_id=" + no);
      return result;
    }
  }

  @Override
  public List<PhotoBoard> findAllByRecipeNo(int RecipeNo) throws Exception {
    try (Connection con = DriverManager.getConnection(jdbcUrl, username, password);
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery( //
            "select photo_id, titl, cdt, vw_cnt, recipe_id" //
                + " from rms_photo" //
                + " where recipe_id=" + RecipeNo //
                + " order by photo_id desc")) {

      ArrayList<PhotoBoard> list = new ArrayList<>();

      while (rs.next()) {
        PhotoBoard photoBoard = new PhotoBoard();
        photoBoard.setNo(rs.getInt("photo_id"));
        photoBoard.setTitle(rs.getString("titl"));
        photoBoard.setCreatedDate(rs.getDate("cdt"));
        photoBoard.setViewCount(rs.getInt("vw_cnt"));

        list.add(photoBoard);
      }
      return list;
    }
  }
}

