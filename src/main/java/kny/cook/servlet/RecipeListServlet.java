package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.RecipeObjectFileDao;

public class RecipeListServlet implements Servlet {

  RecipeObjectFileDao recipeDao;

  public RecipeListServlet(RecipeObjectFileDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(recipeDao.findAll());
  }
}
