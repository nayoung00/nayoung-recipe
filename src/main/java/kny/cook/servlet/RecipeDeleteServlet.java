package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RecipeDeleteServlet implements Servlet {


  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    int no = in.readInt();

    int index = -1;
    for (int i = 0; i < recipes.size(); i++) {
      if (recipes.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      recipes.remove(index);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 레시피가 없습니다.");
    }
  }

}
