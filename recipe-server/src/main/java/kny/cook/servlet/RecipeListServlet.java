package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.Component;
import kny.cook.util.RequestMapping;

@Component
public class RecipeListServlet {

  RecipeService recipeService;

  public RecipeListServlet(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/list")
  public void service(Scanner in, PrintStream out) throws Exception {

    List<Recipe> recipes = recipeService.findAll();

    for (Recipe recipe : recipes) {
      out.printf("%d, %s, %s, %s, %d, %d\n", recipe.getNo(), recipe.getCook(), recipe.getMaterial(),
          recipe.getMethod(), recipe.getExpense(), recipe.getTime());
    }
  }
}
