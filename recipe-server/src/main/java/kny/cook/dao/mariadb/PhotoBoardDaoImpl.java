package kny.cook.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.Recipe;
import kny.cook.sql.DataSource;

public class PhotoBoardDaoImpl implements PhotoBoardDao {
  DataSource dataSource;

  public PhotoBoardDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;

  }

  @Override
  public int insert(PhotoBoard photoBoard) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "insert into rms_photo(titl,recipe_id) values(?,?)", Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, photoBoard.getTitle());
      stmt.setInt(2, photoBoard.getRecipe().getNo());

      int result = stmt.executeUpdate();
      try (ResultSet generatedKeySet = stmt.getGeneratedKeys()) {
        generatedKeySet.next();
        photoBoard.setNo(generatedKeySet.getInt(1));
      }
      return result;
    }
  }

  @Override
  public PhotoBoard findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("select" + " p.titl," + " p.cdt,"
            + " p.vw_cnt," + " r.recipe_id," + " r.cook" + " from rms_photo p"
            + " inner join rms_recipe r on p.recipe_id=r.recipe_id" + " where photo_id=?")) {
      stmt.setInt(1, no);
      try (ResultSet rs = stmt.executeQuery()) {
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
  }

  @Override
  public int update(PhotoBoard photoboard) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("update rms_photo set titl=?, where photo_id=?")) {

      stmt.setString(1, photoboard.getTitle());
      stmt.setInt(2, photoboard.getNo());
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("delete from rms_photo where photo_id=?")) {
      stmt.setInt(1, no);
      return stmt.executeUpdate();
    }
  }

  @Override
  public List<PhotoBoard> findAllByRecipeNo(int RecipeNo) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select photo_id, titl, cdt, vw_cnt, recipe_id" + " from rms_photo"
                + " where recipe_id=? order by photo_id desc")) {

      stmt.setInt(1, RecipeNo);

      try (ResultSet rs = stmt.executeQuery()) {

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
}

