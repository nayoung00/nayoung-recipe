package nayoung.cooknayoung.handler;

import java.util.AbstractList;
import nayoung.cooknayoung.domain.Recipe;
import nayoung.cooknayoung.util.Prompt;

public class RecipeDetailCommand implements Command {

  AbstractList<Recipe> recipeList;
  Prompt prompt;

  public RecipeDetailCommand(Prompt prompt, AbstractList<Recipe> list) {
    this.prompt = prompt;
    recipeList = list;
  }

  @Override
  public void execute() {
    int index = indexOfRecipe(prompt.inputInt("번호?"));

    if (index == -1) {
      System.out.println("해당 번호의 레시피가 없습니다.");
      return;
    }
    Recipe recipe = this.recipeList.get(index);

    System.out.printf("번호? %d\n", recipe.getNo());
    System.out.printf("요리: %s\n", recipe.getCook());
    System.out.printf("재료: %s\n", recipe.getMaterial());
    System.out.printf("방법 : %s\n", recipe.getMethod());
    System.out.printf("비용: %d\n", recipe.getExpense());
    System.out.printf("시간: %d\n", recipe.getTime());
  }

  private int indexOfRecipe(int no) {
    for (int i = 0; i < this.recipeList.size(); i++) {
      if (this.recipeList.get(i).getNo() == no)
        return i;
    }
    return -1;
  }
}

