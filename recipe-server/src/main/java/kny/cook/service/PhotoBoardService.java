package kny.cook.service;

import java.util.List;
import kny.cook.domain.PhotoBoard;

public interface PhotoBoardService {

  void add(PhotoBoard photoBoard) throws Exception;

  PhotoBoard get(int no) throws Exception;

  void update(PhotoBoard photoBoard) throws Exception;

  void delete(int no) throws Exception;

  List<PhotoBoard> listRecipePhoto(int recipeNo) throws Exception;

}
