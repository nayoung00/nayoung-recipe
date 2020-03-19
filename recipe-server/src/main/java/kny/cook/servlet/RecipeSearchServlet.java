package kny.cook.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeSearchServlet implements Servlet {
  RecipeDao recipeDao;

  public RecipeSearchServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    HashMap<String, Object> params = new HashMap<>();

    String keyword = Prompt.getString(in, out, "요리명?");
    if (keyword.length() > 0) {
      params.put("cook", keyword);
    }

    keyword = Prompt.getString(in, out, "재료?");
    if (keyword.length() > 0) {
      params.put("met", keyword);
    }

    int value = Prompt.getInt(in, out, "비용?");
    if (value > 0) {
      params.put("expense", value);
    }

    value = Prompt.getInt(in, out, "시간?");
    if (value > 0) {
      params.put("time", value);
    }

    out.println("--------------------------------");
    out.println("[검색결과]");
    out.println();

    List<Recipe> recipes = recipeDao.findByKeyword(params);
    for (Recipe recipe : recipes) {
      out.printf("%d, %s, %s, %s, %d, %d\n", recipe.getNo(), recipe.getCook(), recipe.getMaterial(),
          recipe.getMethod(), recipe.getExpense(), recipe.getTime());
    }
  }
}
