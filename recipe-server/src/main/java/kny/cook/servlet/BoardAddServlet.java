package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;
import kny.cook.util.Prompt;

public class BoardAddServlet implements Servlet {

  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Board board = new Board();


    board.setTitle(Prompt.getString(in, out, "제목? "));

    if (boardService.insert(board) > 0) {
      out.println("새 게시글을 등록했습니다.");

    } else {
      out.println("게시글 등록에 실패했습니다.");
    }
  }
}
