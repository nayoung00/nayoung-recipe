package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.Component;
import kny.cook.util.Prompt;

@Component("/recipe/detail")
public class RecipeDetailServlet implements Servlet {

  RecipeService recipeService;

  public RecipeDetailServlet(RecipeService recipeService) {
    this.recipeService = recipeService;

  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    Recipe recipe = recipeService.findByNo(no);

    if (recipe != null) {
      out.printf("번호: %d\n", recipe.getNo());
      out.printf("요리: %s\n", recipe.getCook());
      out.printf("재료: %s\n", recipe.getMaterial());
      out.printf("방법: %s\n", recipe.getMethod());
      out.printf("비용: %d\n", recipe.getExpense());
      out.printf("시간: %d\n", recipe.getTime());

    } else {
      out.println("해당 번호의 레시피가 없습니다.");
    }
  }
}
