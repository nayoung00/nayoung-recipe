package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.RequestMapping;

@Component
public class RecipeSearchServlet {
  RecipeService recipeService;

  public RecipeSearchServlet(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/search")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("  <meta charset='UTF-8'>");
    out.println("  <title>레시피 검색</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("  <h1>레시피 검색 결과</h1>");
    out.println("  <table border='1'>");
    out.println("  <tr>");
    out.println("    <th>번호</th>");
    out.println("    <th>요리</th>");
    out.println("    <th>재료</th>");
    out.println("    <th>비용</th>");
    out.println("    <th>시간</th>");
    out.println("  </tr>");

    String keyword = params.get("keyword");

    List<Recipe> recipes = recipeService.search(keyword);
    for (Recipe recipe : recipes) {
      out.printf(
          "<tr><td>%d</td> <a href='/member/detail?no=%d'>%s</a></td>  <td>%s</td> <td>%s</td> <td>%d</td> <td>%d</td></tr>\n",
          recipe.getNo(), recipe.getNo(), recipe.getCook(), recipe.getMaterial(),
          recipe.getMethod(), recipe.getExpense(), recipe.getTime());
    }
    out.println("</table>");
    out.println("</body>");
    out.println("</html>");
  }
}
