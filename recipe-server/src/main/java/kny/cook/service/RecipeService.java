package kny.cook.service;

import java.util.List;
import kny.cook.domain.Recipe;

public interface RecipeService {

  int add(Recipe recipe) throws Exception;

  int delete(int no) throws Exception;

  Recipe get(int recipeNo) throws Exception;

  List<Recipe> findAll() throws Exception;

  List<Recipe> search(String keyword) throws Exception;

  int update(Recipe recipe) throws Exception;


}
