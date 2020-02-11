package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.json.RecipeJsonFileDao;
import kny.cook.domain.Recipe;

public class RecipeUpdateServlet implements Servlet {

  RecipeJsonFileDao recipeDao;

  public RecipeUpdateServlet(RecipeJsonFileDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Recipe recipe = (Recipe) in.readObject();

    if (recipeDao.update(recipe) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 레시피가 없습니다.");
    }
  }
}
