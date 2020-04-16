package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.service.RecipeService;
import kny.cook.util.RequestMapping;

@Component
public class RecipeDeleteServlet {

  RecipeService recipeService;

  public RecipeDeleteServlet(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/add")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {


    out.println("");
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/recipe/list'>");
    out.println(" <title>레시피 삭제</title>");
    out.println(" </head>");
    out.println("<body>");
    out.println(" <h1>레시피 삭제 결과</h1>");

    int no = Integer.parseInt(params.get("no"));


    if (recipeService.delete(no) > 0) {
      out.println("<p>레시피를 삭제했습니다.</p>");
    } else {
      out.println("<p>해당 번호의 레시피가 없습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
