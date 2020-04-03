package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.service.BoardService;
import kny.cook.util.Component;
import kny.cook.util.Prompt;
import kny.cook.util.RequestMapping;

@Component
public class BoardDeleteServlet {

  BoardService boardService;

  public BoardDeleteServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/delete")
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호?");

    if (boardService.delete(no) > 0) {
      out.println("게시글을 삭제했습니다.");
    } else {
      out.println("해당 번호의 게시물이 없습니다.");
    }
  }
}
