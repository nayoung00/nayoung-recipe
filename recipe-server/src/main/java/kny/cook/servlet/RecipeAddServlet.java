package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.RequestMapping;

@Component
public class RecipeAddServlet {

  RecipeService recipeService;

  public RecipeAddServlet(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Recipe recipe = new Recipe();
    recipe.setCook(params.get("cook"));
    recipe.setMaterial(params.get("material"));
    recipe.setMethod(params.get("method"));
    recipe.setExpense(Integer.parseInt(params.get("expense")));
    recipe.setTime(Integer.parseInt(params.get("time")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println(" <meta charset='UTF-8'>");
    out.println(" <title>레시피 등록</title>");
    out.println(" </head>");
    out.println(" <body>");
    out.println(" <h1>레시피 등록 결과</h1>");
    out.println(" <p>새 레시피를 등록 했습니다.</p>");
    out.println(" </body>");
    out.println(" </html>");
  }
}
