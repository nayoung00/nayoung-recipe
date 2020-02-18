package kny.cook.handler;

import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeAddCommand implements Command {
  RecipeDao recipeDao;
  Prompt prompt;

  public RecipeAddCommand(RecipeDao recipeDao, Prompt prompt) {

    this.recipeDao = recipeDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Recipe recipe = new Recipe();

    recipe.setCook(prompt.inputString("요리? "));
    recipe.setMaterial(prompt.inputString("재료? "));
    recipe.setMethod(prompt.inputString("방법? "));
    recipe.setExpense(prompt.inputInt("비용(원)? "));
    recipe.setTime(prompt.inputInt("시간(분)? "));

    try {
      recipeDao.insert(recipe);
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("등록 실패!");
    }
  }
}
