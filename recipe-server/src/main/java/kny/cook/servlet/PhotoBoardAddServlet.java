package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import kny.cook.domain.Recipe;
import kny.cook.service.PhotoBoardService;
import kny.cook.service.RecipeService;

@WebServlet("/photoboard/add")
public class PhotoBoardAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse respons)
      throws ServletException, IOException {
    try {
      respons.setContentType("text/html;charset=UTF-8");
      PrintWriter out = respons.getWriter();

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      RecipeService recipeService = iocContainer.getBean(RecipeService.class);

      int recipeNo = Integer.parseInt(request.getParameter("RecipeNo"));
      Recipe recipe = recipeService.get(recipeNo);
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>사진 입력</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>사진 입력</h1>");
      out.println("<form action='add'>");
      out.printf("레시피번호: <input name='recipeNo' type='text' value='%d' readonly><br>\n", //
          recipe.getNo());
      out.printf("요리명: %s<br>\n", recipe.getCook());
      out.println("내용:<br>");
      out.println("<textarea name='title' rows='5' cols='60'></textarea><br>");
      out.println("<hr>");
      out.println("사진: <input name='photo1' type='file'><br>");
      out.println("사진: <input name='photo2' type='file'><br>");
      out.println("사진: <input name='photo3' type='file'><br>");
      out.println("사진: <input name='photo4' type='file'><br>");
      out.println("사진: <input name='photo5' type='file'><br>");
      out.println("<button>제출</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    int recipeNo = Integer.parseInt(request.getParameter("RecipeNo"));

    try {
      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      RecipeService recipeService = iocContainer.getBean(RecipeService.class);
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);

      Recipe recipe = recipeService.get(recipeNo);
      if (recipe == null) {
        throw new Exception("레시피 번호가 유효하지 않습니다.");
      }

      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(request.getParameter("title"));
      photoBoard.setRecipe(recipe);

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();
      for (int i = 1; i <= 5; i++) {
        String filepath = request.getParameter("photo" + i);
        if (filepath.length() > 0) {
          photoFiles.add(new PhotoFile().setFilepath(filepath));
        }
      }
      if (photoFiles.size() == 0) {
        throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
      }
      photoBoard.setFiles(photoFiles);
      photoBoardService.add(photoBoard);

      response.sendRedirect("list?recipeNo=" + recipeNo);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      // 포워드 인쿨르드에서 루트는 현재 웹어플리케이션을 의미한다.
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
