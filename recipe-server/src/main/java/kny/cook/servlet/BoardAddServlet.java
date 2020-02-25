package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;

public class BoardAddServlet implements Servlet {

  BoardDao boardDao;


  public BoardAddServlet(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Board board = new Board();

    out.println("제목? \n!{}!");
    out.flush();
    board.setTitle(in.nextLine());

    if (boardDao.insert(board) > 0) {
      out.println("새 게시글을 등록했습니다.");
    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
