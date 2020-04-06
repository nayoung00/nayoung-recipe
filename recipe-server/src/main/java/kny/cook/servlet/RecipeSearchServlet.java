package kny.cook.servlet;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.Prompt;
import kny.cook.util.RequestMapping;

@Component
public class RecipeSearchServlet {
  RecipeService recipeService;

  public RecipeSearchServlet(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/search")
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

    List<Recipe> recipes = recipeService.search(params);
    for (Recipe recipe : recipes) {
      out.printf("%d, %s, %s, %s, %d, %d\n", recipe.getNo(), recipe.getCook(), recipe.getMaterial(),
          recipe.getMethod(), recipe.getExpense(), recipe.getTime());
    }
  }
}
