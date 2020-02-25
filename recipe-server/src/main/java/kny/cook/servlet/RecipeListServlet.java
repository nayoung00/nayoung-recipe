package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeListServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeListServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    List<Recipe> recipes = recipeDao.findAll();

    for (Recipe recipe : recipes) {
      out.printf("%d, %s, %s, %s, %d, %d\n", recipe.getNo(), recipe.getCook(), recipe.getMaterial(),
          recipe.getMethod(), recipe.getExpense(), recipe.getTime());
    }
  }
}
