package kny.cook.handler;

import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeListCommand implements Command {
  RecipeDao recipeDao;

  public RecipeListCommand(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void execute() {
    try {
      List<Recipe> recipes = recipeDao.findAll();

      for (Recipe recipe : recipes) {
        System.out.printf("%d, %s, %s, %s, %d, %d\n", recipe.getNo(), recipe.getCook(),
            recipe.getMaterial(), recipe.getMethod(), recipe.getExpense(), recipe.getTime());

        System.out.println("recipe");
      }
    } catch (

    Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}
