package kny.cook.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeAddCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public RecipeAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Recipe recipe = new Recipe();

    recipe.setNo(prompt.inputInt("번호? "));
    recipe.setCook(prompt.inputString("요리? "));
    recipe.setMaterial(prompt.inputString("재료? "));
    recipe.setMethod(prompt.inputString("방법? "));
    recipe.setExpense(prompt.inputInt("비용(원)? "));
    recipe.setTime(prompt.inputInt("시간(분)? "));

    try {
      out.writeUTF("/recipe/add");
      out.writeObject(recipe);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("통신 오류 발생!");
    }
  }
}
