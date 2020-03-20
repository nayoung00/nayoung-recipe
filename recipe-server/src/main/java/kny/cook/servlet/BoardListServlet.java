package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;

public class BoardListServlet implements Servlet {

  BoardService boardService;

  public BoardListServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Board> boards = boardService.findAll();
    for (Board board : boards) {
      out.printf("=> %d, %s, %s, %d\n", board.getNo(), board.getTitle(), board.getDate(),
          board.getViewCount());
    }
  }
}
