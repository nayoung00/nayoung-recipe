package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Recipe;

public class RecipeAddServlet implements Servlet {

  List<Recipe> recipes;

  public RecipeAddServlet(List<Recipe> recipes) {
    this.recipes = recipes;

  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    Recipe recipe = (Recipe) in.readObject();

    int i = 0;
    for (; i < recipes.size(); i++) {
      if (recipes.get(i).getNo() == recipe.getNo()) {
        break;
      }
    }
    if (i == recipes.size()) {
      recipes.add(recipe);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 레시피가 있습니다.");
    }
  }

}
