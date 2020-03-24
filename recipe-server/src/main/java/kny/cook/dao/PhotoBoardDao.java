package kny.cook.dao;

import java.util.List;
import kny.cook.domain.PhotoBoard;

public interface PhotoBoardDao {

  int insert(PhotoBoard photoboard) throws Exception;

  PhotoBoard findByNo(int no) throws Exception;

  int update(PhotoBoard photoBoard) throws Exception;

  int delete(int no) throws Exception;

  List<PhotoBoard> findAllByRecipeNo(int RecipeNo) throws Exception;
}
