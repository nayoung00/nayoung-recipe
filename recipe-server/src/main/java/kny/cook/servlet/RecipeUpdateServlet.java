package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.Prompt;

public class RecipeUpdateServlet implements Servlet {

  RecipeService recipeService;

  public RecipeUpdateServlet(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    Recipe old = recipeService.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 레시피가 없습니다.");
      return;
    }
    Recipe recipe = new Recipe();

    recipe.setNo(no);
    recipe
        .setCook(Prompt.getString(in, out, String.format("요리(%s)?", old.getCook(), old.getCook())));
    recipe.setMaterial(
        Prompt.getString(in, out, String.format("재료(%s)?", old.getMaterial(), old.getMaterial())));
    recipe.setMethod(
        Prompt.getString(in, out, String.format("방법(%s)?", old.getMethod(), old.getMethod())));
    recipe.setExpense(
        Prompt.getInt(in, out, String.format("비용(%d)?", old.getExpense(), old.getExpense())));
    recipe.setTime(Prompt.getInt(in, out, String.format("시간(%d)?", old.getTime(), old.getTime())));



    if (recipeService.update(recipe) > 0) {
      out.println("레시피를 변경했습니다.");
    } else {
      out.println("레시피 변경에 실패했습니다.");
    }
  }
}
