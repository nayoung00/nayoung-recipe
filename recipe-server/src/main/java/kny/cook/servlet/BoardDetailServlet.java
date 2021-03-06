package kny.cook.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;


@WebServlet("/board/detail")
public class BoardDetailServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      BoardService boardService = iocContainer.getBean(BoardService.class);

      int no = Integer.parseInt(request.getParameter("no"));
      Board board = boardService.get(no);
      if (board == null) {
        throw new Exception("<p>해당 번호의 게시물이 없습니다.</p>");
      }

      // JSP가 출력할 때 사용할 수 있도록 조회 결과를 ServletRequest 보관소에 담는다.
      request.setAttribute("board", board);

      // 출력을 담당할 JSP를 인클루딩 한다.
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/board/detail.jsp").include(request, response);

    } catch (Exception e) {

      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      // 포워드 인쿨르드에서 루트는 현재 웹어플리케이션을 의미한다.
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
