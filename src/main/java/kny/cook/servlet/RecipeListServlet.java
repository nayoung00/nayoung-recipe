package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Recipe;

public class RecipeListServlet implements Servlet {

  List<Recipe> recipes;

  public RecipeListServlet(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(recipes);
  }
}
