package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
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

    out.println(" <!DOCTYPE html>");
    out.println("<html>");
    out.println(" <head>");
    out.println(" <meta charset='UTF-8'>");
    out.println("  <title>레시피 목록</title>");
    out.println(" <body>");
    out.println("  <h1> 레시피 </h1>");
    out.println("  </body>");
    out.println("  <a href='/board/addForm'> 새 글 </a><br>");
    out.println("  <table border='14'>");
    out.println("  <tr>");
    out.println(" <th>번호</th>");
    out.println("  <th>요리</th>");
    out.println(" <th>재료</th>");
    out.println(" <th>방법</th>");
    out.println(" <th>비용</th>");
    out.println(" <th>시간</th>");
    out.println(" </tr>");
    for (Recipe recipe : recipes) {
      out.printf(
          "<tr><td>%d</td>, <td><a href='/recipe/detail?no=%d'>%s</a></td>, <td>%s</td>, <td>%s</td>, <td>%d</td>, <td>%d</td></tr>\n",
          recipe.getNo(), recipe.getNo(), recipe.getCook(), recipe.getMaterial(),
          recipe.getMethod(), recipe.getExpense(), recipe.getTime());
    }
    out.println("</head>");
    out.println("</html>");
  }
}
