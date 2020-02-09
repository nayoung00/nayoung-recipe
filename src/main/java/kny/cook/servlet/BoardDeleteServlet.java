package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Board;

public class BoardDeleteServlet implements Servlet {

  List<Board> boards;

  public BoardDeleteServlet(List<Board> boards) {
    this.boards = boards;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();



    int index = -1;
    for (int i = 0; i < boards.size(); i++) {
      if (boards.get(i).getNo() == no) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      boards.remove(index);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
