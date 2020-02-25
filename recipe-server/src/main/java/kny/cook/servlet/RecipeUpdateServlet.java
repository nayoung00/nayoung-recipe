package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeUpdateServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeUpdateServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    Recipe old = recipeDao.findByNo(no);

    if (old == null) {
      out.println("해당 번호의 레시피가 없습니다.");
      return;
    }
    Recipe recipe = new Recipe();
    recipe.setNo(no);

    out.printf("요리(%s)? \n!{}!\n", old.getCook());
    recipe.setCook(in.nextLine());
    out.printf("재료(%s)? \n!{}!\n", old.getMaterial());
    recipe.setMaterial(in.nextLine());
    out.printf("방법(%s)? \n!{}!\n", old.getMethod());
    recipe.setMethod(in.nextLine());
    out.printf("비용(%d)? \n!{}!\n", old.getExpense());
    recipe.setExpense(in.nextInt());
    out.printf("시간(%d)? \n!{}!\n", old.getTime());
    recipe.setTime(in.nextInt());

    out.flush();

    if (recipeDao.update(recipe) > 0) {
      out.println("레시피를 변경했습니다.");
    } else {
      out.println("레시피 변경에 실패했습니다.");
    }
  }
}
