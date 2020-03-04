package kny.cook.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import kny.cook.dao.PhotoFileDao;
import kny.cook.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

	Connection con;
	
	public PhotoFileDaoImpl(Connection con) {
		this.con = con;
	}
	
	@Override
	public int insert(PhotoFile photoFile) throws Exception {
		 try (Statement stmt = con.createStatement()) {

			 String query = String.format("insert into rms_photo_file(photo_id, file_path) values(%d, '%s')", photoFile.getBoardNo(), photoFile.getFilepath());
		      int result = stmt.executeUpdate(query);
		      return result;	
		 }
	}
	
	@Override
	public List<PhotoFile> findAll(int boardNo) throws Exception {
	    try (Statement stmt = con.createStatement();
	            ResultSet rs = stmt.executeQuery( 
	                "select photo_file_id, photo_id, file_path"
	                    + " from rms_photo_file" 
	                    + " where photo_id=" + boardNo 
	                    + " order by photo_file_id asc")) {
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

	
	@Override
	public int deleteAll(int boardNo) throws Exception {
	    try (Statement stmt = con.createStatement()) {
	        int result = stmt.executeUpdate( //
	            "delete from rms_photo_file" //
	                + " where photo_id=" + boardNo);
	        return result;
	 }
  }
}