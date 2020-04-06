package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;
import kny.cook.util.Prompt;
import kny.cook.util.RequestMapping;

@Component
public class BoardAddServlet {

  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/add")
  public void service(Scanner in, PrintStream out) throws Exception {
    Board board = new Board();
    board.setTitle(Prompt.getString(in, out, "제목? "));
    boardService.add(board);
    out.println("새 게시글을 등록했습니다.");

  }
}
