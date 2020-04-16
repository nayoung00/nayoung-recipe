package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;
import kny.cook.util.RequestMapping;

@Component
public class BoardUpdateServlet {

  BoardService boardService;

  public BoardUpdateServlet(BoardService boardService) {
    this.boardService = boardService;
  }

  @RequestMapping("/board/update")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    Board board = new Board();
    board.setTitle(params.get("title"));
    board.setNo(Integer.parseInt(params.get("no")));

    out.println("");
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println(" <head>");
    out.println("<meta charset='UTF-8'>");
    out.println(" <meta http-equiv='refresh' content='1;url=/board/list'>");
    out.println("<title>게시글 변경</title>");
    out.println(" </head>");
    out.println("<body>");
    out.println(" <h1> 게시물 변경 결과</h1>");


    if (boardService.update(board) > 0) {
      out.println("<p>게시글을 변경했습니다.</p>");
    } else {
      out.println("<p>게시글 변경에 실패했습니다.</p>");
    }
    out.println("</body>");
    out.println(" </html>");
  }
}
