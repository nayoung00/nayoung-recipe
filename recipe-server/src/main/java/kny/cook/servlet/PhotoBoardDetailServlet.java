package kny.cook.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.PhotoBoard;
import kny.cook.service.PhotoBoardService;


@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    int recipeNo = 0;
    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
      int no = Integer.parseInt(request.getParameter("no"));

      PhotoBoard photoBoard = photoBoardService.get(no);
      if (photoBoard == null) {
        throw new Exception("<p>해당 번호의 사진게시물이 없습니다.</p>");
      }
      request.setAttribute("photoBoard", photoBoard);

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/photoboard/detail.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?recipeNo=" + recipeNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
