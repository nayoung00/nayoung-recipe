package kny.cook.handler;

import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeDetailCommand implements Command {
  RecipeDao recipeDao;
  Prompt prompt;


  public RecipeDetailCommand(RecipeDao recipeDao, Prompt prompt) {
    this.recipeDao = recipeDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호?");

      Recipe recipe = recipeDao.findByNo(no);

      System.out.printf("번호? %d\n", recipe.getNo());
      System.out.printf("요리: %s\n", recipe.getCook());
      System.out.printf("재료: %s\n", recipe.getMaterial());
      System.out.printf("방법 : %s\n", recipe.getMethod());
      System.out.printf("비용: %d\n", recipe.getExpense());
      System.out.printf("시간: %d\n", recipe.getTime());
    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }
}
