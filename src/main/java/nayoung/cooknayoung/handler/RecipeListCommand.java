package nayoung.cooknayoung.handler;

import java.util.AbstractList;
import java.util.Iterator;
import nayoung.cooknayoung.domain.Recipe;

public class RecipeListCommand implements Command {

  AbstractList<Recipe> recipeList;


  public RecipeListCommand(AbstractList<Recipe> list) {
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

