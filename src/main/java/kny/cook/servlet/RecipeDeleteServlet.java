package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.json.RecipeJsonFileDao;

public class RecipeDeleteServlet implements Servlet {

  RecipeJsonFileDao recipeDao;

  public RecipeDeleteServlet(RecipeJsonFileDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (recipeDao.delete(no) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 레시피가 없습니다.");
    }
  }

}
