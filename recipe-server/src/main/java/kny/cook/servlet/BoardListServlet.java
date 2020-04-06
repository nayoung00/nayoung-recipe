package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;
import kny.cook.util.RequestMapping;

@Component
public class BoardListServlet {

  BoardService boardService;

  public BoardListServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/list")
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Board> boards = boardService.list();
    for (Board board : boards) {
      out.printf("%d, %s, %s, %d\n", board.getNo(), board.getTitle(), board.getDate(),
          board.getViewCount());
    }
  }
}
