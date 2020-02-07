package kny.cook.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeDetailCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;


  public RecipeDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호?");
      out.writeUTF("/recipe/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      Recipe recipe = (Recipe) in.readObject();

      System.out.printf("번호? %d\n", recipe.getNo());
      System.out.printf("요리: %s\n", recipe.getCook());
      System.out.printf("재료: %s\n", recipe.getMaterial());
      System.out.printf("방법 : %s\n", recipe.getMethod());
      System.out.printf("비용: %d\n", recipe.getExpense());
      System.out.printf("시간: %d\n", recipe.getTime());
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류!");
    }
  }
}
