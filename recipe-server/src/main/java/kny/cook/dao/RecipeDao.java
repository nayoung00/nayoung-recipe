package kny.cook.dao;

import java.util.List;
import kny.cook.domain.Recipe;

public interface RecipeDao {

  int insert(Recipe recipe) throws Exception;

  List<Recipe> findAll() throws Exception;

  Recipe findByNo(int no) throws Exception;

  int update(Recipe recipe) throws Exception;

  int delete(int no) throws Exception;

  default List<Recipe> findByKeyword(String keyword) throws Exception {
    return null;
  }
}
