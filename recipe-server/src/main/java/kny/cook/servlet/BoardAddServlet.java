package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;
import kny.cook.util.RequestMapping;

@Component
public class BoardAddServlet {

  BoardService boardService;

  public BoardAddServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/add")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    Board board = new Board();
    board.setTitle(params.get("title"));
    boardService.add(board);

    out.println(" <!DOCTYPE html>");
    out.println(" <html>");
    out.println(" <head>");
    out.println(" <meta charset='UTF-8'>");
    out.println(" <meta http-equiv='refresh' content='2;url=/board/list'>");
    out.println(" <title>게시물 입력</title>");
    out.println(" </head>");
    out.println(" <body>");
    out.println("<h1>게시물 입력결과</h1>");
    out.println("<p>새 게시글을 등록했습니다.</p>");
    out.println(" </body>");
    out.println(" </html>");
  }
}
