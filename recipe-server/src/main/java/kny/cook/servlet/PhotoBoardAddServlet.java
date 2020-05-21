package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@MultipartConfig(maxFileSize = 500000)
public class PhotoBoardAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int recipeNo = Integer.parseInt(request.getParameter("reicpeNo"));
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      RecipeService recipeService = iocContainer.getBean(RecipeService.class);

      Recipe recipe = recipeService.get(recipeNo);
      request.setAttribute("recipe", recipe);

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/photoboard/form.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?recipeNo=" + recipeNo);
      request.getRequestDispatcher("/error").forward(request, response);

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
      request.setAttribute("url", "list?recipeNo=" + recipeNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
