package nayoung.cooknayoung.handler;

import java.util.AbstractList;
import nayoung.cooknayoung.domain.Recipe;
import nayoung.cooknayoung.util.Prompt;

public class RecipeUpdateCommand implements Command {

  AbstractList<Recipe> recipeList;
  Prompt prompt;

  public RecipeUpdateCommand(Prompt prompt, AbstractList<Recipe> list) {
    this.prompt = prompt;
    recipeList = list;
  }

  @Override
  public void execute() {
    int index = indexOfRecipe(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 레시피가 없습니다. ");
      return;
    }

    Recipe oldRecipe = this.recipeList.get(index);
    Recipe newRecipe = new Recipe();

    newRecipe.setNo(oldRecipe.getNo());

    newRecipe.setCook(
        prompt.inputString(String.format("요리(%s)?", oldRecipe.getCook(), oldRecipe.getCook())));

    newRecipe.setMaterial(prompt
        .inputString(String.format("재료(%s)?", oldRecipe.getMaterial(), oldRecipe.getMaterial())));


    newRecipe.setMethod(
        prompt.inputString(String.format("방법(%s)?", oldRecipe.getMethod(), oldRecipe.getMethod())));

    newRecipe.setExpense(
        prompt.inputInt(String.format("비용(%d)?", oldRecipe.getExpense()), oldRecipe.getExpense()));

    newRecipe.setTime(
        prompt.inputInt(String.format("시간(%d)?", oldRecipe.getTime()), oldRecipe.getTime()));


    if (oldRecipe.equals(newRecipe)) {
      System.out.println("레시피 변경을 취소하였습니다.");
      return;
    }
    this.recipeList.set(index, newRecipe);
    System.out.println("요리를 변경했습니다.");
  }

  private int indexOfRecipe(int no) {
    for (int i = 0; i < this.recipeList.size(); i++) {
      if (this.recipeList.get(i).getNo() == no)
        return i;
    }
    return -1;
  }
}

