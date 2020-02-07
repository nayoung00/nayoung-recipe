package kny.cook.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.util.Prompt;

public class RecipeDeleteCommand implements Command {

  ObjectOutputStream out;
  ObjectInputStream in;
  Prompt prompt;

  public RecipeDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {

    this.out = out;
    this.in = in;
    this.prompt = prompt;

  }


  @Override
  public void execute() {
    int no = prompt.inputInt("번호? ");

    try {
      out.writeUTF("/recipe/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();

      if (response.equals("FAIL")) {
        System.out.println(in.readUTF());
        return;
      }
      System.out.println("게시글을 삭제했습니다.");


      System.out.println("레시피를 삭제했습니다.");
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
