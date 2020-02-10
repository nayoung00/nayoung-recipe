package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import kny.cook.dao.BoardObjectFileDao;
import kny.cook.domain.Board;

public class BoardDetailServlet implements Servlet {

  BoardObjectFileDao boardDao;

  public BoardDetailServlet(BoardObjectFileDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (boardDao.delete(no) > 0 ) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 게시물이 없습니다.");
    }
  }
}
