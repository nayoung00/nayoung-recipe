package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeAddServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeAddServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;

  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Recipe recipe = new Recipe();
    
    recipe.setCook(Prompt.getString(in, out, "요리? "));
    recipe.setMaterial(Prompt.getString(in, out, "재료? "));
    recipe.setMethod(Prompt.getString(in, out,"방법? "));
    recipe.setExpense(Prompt.getInt(in, out, "비용? "));
    recipe.setTime(Prompt.getInt(in, out, "시간 ? "));
    
    if (recipeDao.insert(recipe) > 0) {
      out.println("새 레시피를 등록했습니다.");
      
      
    } else {
      out.println("레시피 등록에 실패했습니다.");
    }
  }

}
