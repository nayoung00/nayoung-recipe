package kny.cook.service;

import java.util.HashMap;
import java.util.List;
import kny.cook.domain.Recipe;

public interface RecipeService {

  int add(Recipe recipe) throws Exception;

  int delete(int no) throws Exception;

  Recipe findByNo(int no) throws Exception;

  List<Recipe> findAll() throws Exception;

  List<Recipe> search(HashMap<String, Object> params) throws Exception;

  int update(Recipe recipe) throws Exception;

}
