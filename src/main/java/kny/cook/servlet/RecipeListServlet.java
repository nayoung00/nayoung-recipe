package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.json.RecipeJsonFileDao;

public class RecipeListServlet implements Servlet {

  RecipeJsonFileDao recipeDao;

  public RecipeListServlet(RecipeJsonFileDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(recipeDao.findAll());
  }
}
