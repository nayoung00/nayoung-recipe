package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeDetailServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeDetailServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;

  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Recipe recipe = recipeDao.findByNo(no);

    if (recipe != null) {
      out.printf("번호: %d\n", recipe.getNo());
      out.printf("요리: %s\n", recipe.getCook());
      out.printf("재료: %s\n", recipe.getMaterial());
      out.printf("방법: %s\n", recipe.getMethod());
      out.printf("비용: %d\n", recipe.getExpense());
      out.printf("시간: %d\n", recipe.getTime());

    } else {
      out.println("해당 번호의 레시피가 없습니다.");
    }
  }
}
