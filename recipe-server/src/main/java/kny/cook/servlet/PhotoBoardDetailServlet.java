package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.PhotoFile;
import kny.cook.service.PhotoBoardService;


@WebServlet("/photoboard/detail")
public class PhotoBoardDetailServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
      int no = Integer.parseInt(request.getParameter("no"));

      PhotoBoard photoBoard = photoBoardService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>사진 상세정보</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>사진 상세정보</h1>");

      if (photoBoard != null) {
        out.println("<form action='update' method='post'>");
        out.printf("번호: <intput name='no' type='text' readonly value='%d'><br>\n",
            photoBoard.getNo());
        out.printf("제목: <textarea name='title' rows='1' cols='60'>%s</textarea><br>\n",
            photoBoard.getTitle());
        out.printf("등록일: %s<br>\n", photoBoard.getCreatedDate());
        out.printf("조회수: %d<br>\n", photoBoard.getViewCount());
        out.printf("요리: %s<br>\n", photoBoard.getRecipe().getCook());
        out.println("<hr>");
        out.println("사진 파일:<br>");
        out.println("<ul>\n");

        for (PhotoFile photoFile : photoBoard.getFiles()) {
          out.printf("<li> %s</li>\n", photoFile.getFilepath());
        }
        out.println("</ul>");
        out.println("사진: <input name='photo1' type='file'><br>");
        out.println("사진: <input name='photo2' type='file'><br>");
        out.println("사진: <input name='photo3' type='file'><br>");
        out.println("사진: <input name='photo4' type='file'><br>");
        out.println("사진: <input name='photo5' type='file'><br>");

        out.println("<p><button>변경</button>");
        out.printf("<a href='delete?no=%d&recipeNo=%d'>삭제</a></p>\n", photoBoard.getNo(),
            photoBoard.getRecipe().getNo());
        out.println("</form>");

      } else {
        out.println("<p>해당 번호의 사진 게시글이 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      // 포워드 인쿨르드에서 루트는 현재 웹어플리케이션을 의미한다.
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
