package kny.cook.handler;

import java.util.AbstractList;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeAddCommand implements Command {

  AbstractList<Recipe> recipeList;
  Prompt prompt;

  public RecipeAddCommand(Prompt prompt, AbstractList<Recipe> list) {
    this.prompt = prompt;
    recipeList = list;
  }

  @Override
  public void execute() {
    Recipe recipe = new Recipe();

    recipe.setNo(prompt.inputInt("번호? "));
    recipe.setCook(prompt.inputString("요리? "));
    recipe.setMaterial(prompt.inputString("재료? "));
    recipe.setMethod(prompt.inputString("방법? "));
    recipe.setExpense(prompt.inputInt("비용(원)? "));
    recipe.setTime(prompt.inputInt("시간(분)? "));

    System.out.println();
    recipeList.add(recipe);
    System.out.println("저장하였습니다.");

  }
}

