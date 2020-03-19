package kny.cook.dao;

import java.util.List;
import java.util.Map;
import kny.cook.domain.Recipe;

public interface RecipeDao {

  int insert(Recipe recipe) throws Exception;

  List<Recipe> findAll() throws Exception;

  Recipe findByNo(int no) throws Exception;

  int update(Recipe recipe) throws Exception;

  int delete(int no) throws Exception;

  default List<Recipe> findByKeyword(Map<String, Object> params) throws Exception {
    return null;
  }
}
