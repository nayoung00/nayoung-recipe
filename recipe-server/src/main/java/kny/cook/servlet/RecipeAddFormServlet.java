package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;
import kny.cook.util.RequestMapping;

@Component
public class RecipeAddFormServlet {

  RecipeService recipeService;

  public RecipeAddFormServlet(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @RequestMapping("/recipe/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Recipe recipe = new Recipe();

    out.println("<!DOCTYPE html>");
    out.println(" <html>");
    out.println(" <head>");
    out.println(" <meta charset='UTF-8'>");
    out.println(" <title>레시피 입력</title>");
    out.println(" </head>");
    out.println(" <body>");
    out.println(" <h1>레시피 입력</h1>");
    out.println(" <form action='/recipe/add'>");
    out.println(" 요리:<br>");
    out.println(" <textarea name='cook' rows='1' cols='60'></textarea><br>");
    out.println(" 재료:<br>");
    out.println(" <textarea name='material' rows='1' cols='60'></textarea><br>");
    out.println(" 방법:<br>");
    out.println(" <textarea name='method' rows='1' cols='60'></textarea><br>");
    out.println(" 비용:<br>");
    out.println(" <textarea name='expense' rows='1' cols='60'></textarea><br>");
    out.println(" 시간:<br>");
    out.println(" <textarea name='time' rows='1' cols='60'></textarea><br>");
    out.println(" <button>제출</button>");
    out.println(" </form>");
    out.println(" </body>");
    out.println(" </html>");
  }
}
