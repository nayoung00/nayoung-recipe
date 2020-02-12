package kny.cook.handler;

import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeListCommand implements Command {

  RecipeDao recipeDao;

  public RecipeListCommand(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }


  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      List<Recipe> recipes = recipeDao.findAll();
      for (Recipe r : recipes) {
        System.out.printf("%d, %s, %s, %d, %d\n", r.getNo(), r.getCook(), r.getMethod(),
            r.getExpense(), r.getTime());
      }
    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}

