package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Recipe;

public class RecipeUpdateServlet implements Servlet {

  List<Recipe> recipes;

  public RecipeUpdateServlet(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Recipe recipe = (Recipe) in.readObject();

    int index = -1;
    for (int i = 0; i < recipes.size(); i++) {
      if (recipes.get(i).getNo() == recipe.getNo()) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      recipes.set(index, recipe);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 레시피가 없습니다.");
    }
  }
}
