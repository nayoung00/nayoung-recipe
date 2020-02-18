package kny.cook.handler;

import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeUpdateCommand implements Command {
  RecipeDao recipeDao;
  Prompt prompt;


  public RecipeUpdateCommand(RecipeDao recipeDao, Prompt prompt) {
    this.recipeDao = recipeDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Recipe oldRecipe = null;
      try {
        oldRecipe = recipeDao.findByNo(no);
      } catch (Exception e) {
        System.out.println("해당 번호의 요리가 없습니다.");
        return;
      }

      Recipe newRecipe = new Recipe();
      newRecipe.setNo(oldRecipe.getNo());

      newRecipe.setCook(
          prompt.inputString(String.format("요리(%s)?", oldRecipe.getCook(), oldRecipe.getCook())));

      newRecipe.setMaterial(prompt
          .inputString(String.format("재료(%s)?", oldRecipe.getMaterial(), oldRecipe.getMaterial())));


      newRecipe.setMethod(prompt
          .inputString(String.format("방법(%s)?", oldRecipe.getMethod(), oldRecipe.getMethod())));

      newRecipe.setExpense(prompt.inputInt(String.format("비용(%d)?", oldRecipe.getExpense()),
          oldRecipe.getExpense()));

      newRecipe.setTime(
          prompt.inputInt(String.format("시간(%d)?", oldRecipe.getTime()), oldRecipe.getTime()));

      recipeDao.update(newRecipe);
      System.out.println("요리를 변경했습니다.");
    } catch (Exception e) {
      System.out.println("변경 실패!");
    }
  }
}

