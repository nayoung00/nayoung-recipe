package kny.cook.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kny.cook.dao.PhotoFileDao;
import kny.cook.domain.PhotoFile;
import kny.cook.sql.DataSource;

public class PhotoFileDaoImpl implements PhotoFileDao {

  DataSource dataSource;

  public PhotoFileDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Override
  public int insert(PhotoFile photoFile) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("insert into rms_photo_file(photo_id, file_path) values(?,?)")) {

      stmt.setInt(1, photoFile.getBoardNo());
      stmt.setString(2, photoFile.getFilepath());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<PhotoFile> findAll(int boardNo) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("select photo_file_id, photo_id, file_path"
            + " from rms_photo_file" + " where photo_id=? order by photo_file_id asc")) {

      stmt.setInt(1, boardNo);

      try (ResultSet rs = stmt.executeQuery()) {
        ArrayList<PhotoFile> list = new ArrayList<>();
        while (rs.next()) {
          list.add(new PhotoFile() //
              .setNo(rs.getInt("photo_file_id")) //
              .setFilepath(rs.getString("file_path")) //
              .setBoardNo(rs.getInt("photo_id")));
        }
        return list;
      }
    }
  }

  @Override
  public int deleteAll(int boardNo) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("delete from rms_photo_file where photo_id=?")) {
      stmt.setInt(1, boardNo);
      return stmt.executeUpdate();
    }
  }
}
