package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeDetailServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeDetailServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;

  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Recipe recipe = recipeDao.findByNo(no);

    if (recipe != null) {
      out.writeUTF("OK");
      out.writeObject(recipe);
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 레시피가 없습니다.");
    }

  }

}
