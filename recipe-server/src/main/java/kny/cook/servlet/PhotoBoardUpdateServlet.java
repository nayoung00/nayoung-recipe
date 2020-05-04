package kny.cook.servlet;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/photoboard/update")
public class PhotoBoardUpdateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    int no = Integer.parseInt(request.getParameter("no"));
    int recipeNo = 0;
    try {

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      PhotoBoard photoBoard = photoBoardService.get(no);
      photoBoard.setTitle(request.getParameter("title"));


      ArrayList<PhotoFile> photoFiles = new ArrayList<>();
      for (int i = 1; i <= 5; i++) {
        String filepath = request.getParameter("photo" + i);
        if (filepath.length() > 0) {
          photoFiles.add(new PhotoFile().setFilepath(filepath));
        }
      }
      if (photoFiles.size() > 0) {
        photoBoard.setFiles(photoFiles);
      } else {
        photoBoard.setFiles(null);
      }
      recipeNo = photoBoard.getRecipe().getNo();
      photoBoardService.update(photoBoard);
      response.sendRedirect("list?recipeNo=" + recipeNo);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      // 포워드 인쿨르드에서 루트는 현재 웹어플리케이션을 의미한다.
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
