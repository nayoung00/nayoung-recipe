package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Board;

public class BoardUpdateServlet implements Servlet {

  List<Board> boards;

  public BoardUpdateServlet(List<Board> boards) {
    this.boards = boards;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    Board board = (Board) in.readObject();

    int index = -1;
    for (int i = 0; i < boards.size(); i++) {
      if (boards.get(i).getNo() == board.getNo()) {
        index = i;
        break;
      }
    }
    if (index != -1) {
      boards.set(index, board);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }

}
