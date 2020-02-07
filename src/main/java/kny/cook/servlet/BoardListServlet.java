package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Board;

public class BoardListServlet implements Servlet {

  List<Board> boards;

  public BoardListServlet(List<Board> boards) {
    this.boards = boards;
  }

  @Override
  public void service(ObjectOutputStream out, ObjectInputStream in) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(boards);
  }
}
