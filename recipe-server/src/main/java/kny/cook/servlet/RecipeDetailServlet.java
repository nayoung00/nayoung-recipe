package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.RequestMapping;

@Component
public class RecipeDetailServlet {

  RecipeService recipeService;

  public RecipeDetailServlet(RecipeService recipeService) {
    this.recipeService = recipeService;

  }

  @RequestMapping("/recipe/detail")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    int no = Integer.parseInt(params.get("no"));
    Recipe recipe = recipeService.get(no);

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println(" <head>");
    out.println("<meta charset='UTF-8'>");
    out.println(" <title>레시피 상세정보</title>");
    out.println(" </head>");
    out.println(" <body>");
    out.println(" <h1> 레시피 상세정보</h1>");

    if (recipe != null) {
      out.printf("번호: %d<br>\n", recipe.getNo());
      out.printf("요리: %s<br>\n", recipe.getCook());
      out.printf("재료: %s<br>\n", recipe.getMaterial());
      out.printf("방법: %s<br>\n", recipe.getMethod());
      out.printf("비용: %d<br>\n", recipe.getExpense());
      out.printf("시간: %d<br>\n", recipe.getTime());
      out.printf("<p><a href='/recipe/delete?no=%d'삭제</a>\n", recipe.getNo());
      out.printf("<a href='/recipe/updateForm?no=%d'>변경</a></p>\n", recipe.getNo());

    } else {
      out.println("<p>해당 번호의 레시피가 없습니다.</p>");
    }
    out.println(" </body>");
    out.println(" </html>");
  }
}
