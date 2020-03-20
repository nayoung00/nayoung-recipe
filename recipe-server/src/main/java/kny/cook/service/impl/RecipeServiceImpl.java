package kny.cook.service.impl;

import java.util.HashMap;
import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;

public class RecipeServiceImpl implements RecipeService {

  RecipeDao recipeDao;

  public RecipeServiceImpl(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public int insert(Recipe recipe) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete(int no) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Recipe findByNo(int no) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Recipe> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Recipe> findByKeyword(HashMap<String, Object> params) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int update(Recipe recipe) {
    // TODO Auto-generated method stub
    return 0;
  }



}
