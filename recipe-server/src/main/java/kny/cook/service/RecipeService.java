package kny.cook.service;

import java.util.HashMap;
import java.util.List;
import kny.cook.domain.Recipe;

public interface RecipeService {

  int insert(Recipe recipe);

  int delete(int no);

  Recipe findByNo(int no);

  List<Recipe> findAll();

  List<Recipe> findByKeyword(HashMap<String, Object> params);

  int update(Recipe recipe);

}
