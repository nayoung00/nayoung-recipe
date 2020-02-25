package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.RecipeDao;

public class RecipeDeleteServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeDeleteServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    if (recipeDao.delete(no) > 0) {
      out.println("레시피를 삭제했습니다.");
    } else {
      out.println("해당 번호의 레시피가 없습니다.");
    }
  }

}
