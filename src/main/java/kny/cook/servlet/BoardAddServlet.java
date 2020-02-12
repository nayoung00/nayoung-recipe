package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;

public class BoardAddServlet implements Servlet {

  BoardDao boardDao;


  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    Board board = (Board) in.readObject();

    if (boardDao.insert(board) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("같은 번호의 게시물이 있습니다.");
    }
  }

}
