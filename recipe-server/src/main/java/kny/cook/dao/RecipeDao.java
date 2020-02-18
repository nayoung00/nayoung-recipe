package kny.cook.dao;

import java.util.List;
import kny.cook.domain.Recipe;

public interface RecipeDao {

  public int insert(Recipe recipe) throws Exception;

  public List<Recipe> findAll() throws Exception;

  public Recipe findByNo(int no) throws Exception;

  public int update(Recipe recipe) throws Exception;

  public int delete(int no) throws Exception;

}
