package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.RecipeDao;

public class RecipeListServlet implements Servlet {

  RecipeDao recipeDao;

  public RecipeListServlet(RecipeDao recipeDao) {
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(recipeDao.findAll());
  }
}
