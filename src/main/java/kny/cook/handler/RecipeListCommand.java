package kny.cook.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Recipe;

public class RecipeListCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;


  public RecipeListCommand(ObjectOutputStream out, ObjectInputStream in) {
    this.out = out;
    this.in = in;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {
      out.writeUTF("/recipe/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      List<Recipe> recipes = (List<Recipe>) in.readObject();
      for (Recipe r : recipes) {
        System.out.printf("%d, %s, %s, %d, %d\n", r.getNo(), r.getCook(), r.getMethod(),
            r.getExpense(), r.getTime());
      }
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
