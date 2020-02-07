package kny.cook.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.domain.Recipe;
import kny.cook.util.Prompt;

public class RecipeUpdateCommand implements Command {
  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;


  public RecipeUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
    this.out = out;
    this.in = in;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");
      out.writeUTF("/recipe/update");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      Recipe oldRecipe = (Recipe) in.readObject();
      Recipe newRecipe = new Recipe();

      newRecipe.setNo(oldRecipe.getNo());

      newRecipe.setCook(
          prompt.inputString(String.format("요리(%s)?", oldRecipe.getCook(), oldRecipe.getCook())));

      newRecipe.setMaterial(prompt
          .inputString(String.format("재료(%s)?", oldRecipe.getMaterial(), oldRecipe.getMaterial())));


      newRecipe.setMethod(prompt
          .inputString(String.format("방법(%s)?", oldRecipe.getMethod(), oldRecipe.getMethod())));

      newRecipe.setExpense(prompt.inputInt(String.format("비용(%d)?", oldRecipe.getExpense()),
          oldRecipe.getExpense()));

      newRecipe.setTime(
          prompt.inputInt(String.format("시간(%d)?", oldRecipe.getTime()), oldRecipe.getTime()));


      if (oldRecipe.equals(newRecipe)) {
        System.out.println("레시피 변경을 취소하였습니다.");
        return;
      }

      out.writeUTF("/recipe/update");
      out.writeObject(newRecipe);
      out.flush();

      response = in.readUTF();
      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }

      System.out.println("요리를 변경했습니다.");
    } catch (Exception e) {
      System.out.println("명령 중 실행 오류!");
    }
  }
}

