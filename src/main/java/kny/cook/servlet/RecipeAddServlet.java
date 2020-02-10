package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.RecipeObjectFileDao;
import kny.cook.domain.Recipe;

public class RecipeAddServlet implements Servlet {

  RecipeObjectFileDao recipeDao;

  public RecipeAddServlet(RecipeObjectFileDao recipeDao) {
    this.recipeDao = recipeDao;

  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Recipe recipe = (Recipe) in.readObject();

    if (recipeDao.insert(recipe) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 레시피가 있습니다.");
    }
  }

}
