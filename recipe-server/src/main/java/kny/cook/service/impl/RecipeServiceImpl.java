package kny.cook.service.impl;

import java.util.HashMap;
import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.Component;

@Component
public class RecipeServiceImpl implements RecipeService {

  RecipeDao recipeDao;

  public RecipeServiceImpl(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public int add(Recipe recipe) throws Exception {
    return recipeDao.insert(recipe);
  }

  @Override
  public int delete(int no) throws Exception {
    return recipeDao.delete(no);
  }

  @Override
  public Recipe findByNo(int no) throws Exception {
    return recipeDao.findByNo(no);
  }

  @Override
  public List<Recipe> findAll() throws Exception {
    return recipeDao.findAll();
  }

  @Override
  public List<Recipe> search(HashMap<String, Object> params) throws Exception {
    return recipeDao.findByKeyword(params);
  }

  @Override
  public int update(Recipe recipe) throws Exception {
    return recipeDao.update(recipe);
  }
}
