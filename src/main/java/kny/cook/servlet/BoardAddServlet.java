package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Board;

public class BoardAddServlet implements Servlet {

  List<Board> boards;

  public BoardAddServlet(List<Board> boards) {
    this.boards = boards;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    Board board = (Board) in.readObject();

    int i = 0;
    for (; i < boards.size(); i++) {
      if (boards.get(i).getNo() == board.getNo()) {
        break;
      }
    }
    if (i == boards.size()) {
      boards.add(board);
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }

}
