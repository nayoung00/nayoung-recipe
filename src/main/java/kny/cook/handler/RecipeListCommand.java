package kny.cook.handler;

import java.util.Iterator;
import java.util.List;
import kny.cook.domain.Recipe;

public class RecipeListCommand implements Command {

  List<Recipe> recipeList;


  public RecipeListCommand(List<Recipe> list) {
    recipeList = list;
  }

  @Override
  public void execute() {
    Iterator<Recipe> iterator = recipeList.iterator();
    while (iterator.hasNext()) {
      Recipe r = iterator.next();

      System.out.printf("%d, %s, %s, %d, %d\n", r.getNo(), r.getCook(), r.getMethod(),
          r.getExpense(), r.getTime());
    }
  }
}

