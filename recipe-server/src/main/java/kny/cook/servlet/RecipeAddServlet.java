package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeAddServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeAddServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;

  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Recipe recipe = new Recipe();

    out.println("요리? \n!{}!");
    recipe.setCook(in.nextLine());
    out.println("재료? \n!{}!");
    recipe.setMaterial(in.nextLine());
    out.println("방법? \n!{}!");
    recipe.setMethod(in.nextLine());
    out.println("비용? \n!{}!");
    recipe.setExpense(in.nextInt());
    out.println("시간? \n!{}!");
    recipe.setTime(in.nextInt());

    out.flush();

    if (recipeDao.insert(recipe) > 0) {
      out.println("새 레시피를 등록했습니다.");
    } else {
      out.println("레시피 등록에 실패했습니다.");
    }
  }

}
