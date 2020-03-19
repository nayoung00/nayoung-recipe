package kny.cook.dao;

import java.util.List;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.PhotoFile;

public interface PhotoFileDao {

  int insert(PhotoBoard photoBoard) throws Exception;

  List<PhotoFile> findAll(int boardNo) throws Exception;

  int deleteAll(int boardNo) throws Exception;
}
