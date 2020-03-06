package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.RecipeDao;
import kny.cook.util.Prompt;

public class RecipeDeleteServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeDeleteServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    if (recipeDao.delete(no) > 0) {
      out.println("레시피를 삭제했습니다.");
    } else {
      out.println("해당 번호의 레시피가 없습니다.");
    }
  }

}
