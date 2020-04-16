package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.RequestMapping;

@Component
public class RecipeUpdateServlet {

  RecipeService recipeService;

  public RecipeUpdateServlet(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/update")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    Recipe recipe = new Recipe();

    recipe.setNo(Integer.parseInt(params.get("no")));
    recipe.setCook(params.get("name"));
    recipe.setMaterial(params.get("material"));
    recipe.setMethod(params.get("method"));
    recipe.setExpense(Integer.parseInt(params.get("expense")));
    recipe.setTime(Integer.parseInt(params.get("time")));

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='1;url=/recipe/list'>");
    out.println("<title>레시피 변경</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>레시피 변경 결과</h1>");

    if (recipeService.update(recipe) > 0) {
      out.println("<p>레시피를 변경했습니다.</p>");
    } else {
      out.println("<p>레시피 변경에 실패했습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }
}
