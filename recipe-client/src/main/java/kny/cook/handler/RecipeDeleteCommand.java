package kny.cook.handler;

import kny.cook.dao.RecipeDao;
import kny.cook.util.Prompt;

public class RecipeDeleteCommand implements Command {

  RecipeDao recipeDao;
  Prompt prompt;

  public RecipeDeleteCommand(RecipeDao recipeDao, Prompt prompt) {

    this.recipeDao = recipeDao;
    this.prompt = prompt;
  }


  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      if (recipeDao.delete(no) > 0) {
        System.out.println("레시피를 삭제했습니다.");
      } else {
        System.out.println("레시피를 찾을 수 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
