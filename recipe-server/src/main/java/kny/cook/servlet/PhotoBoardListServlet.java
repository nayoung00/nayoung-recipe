package kny.cook.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.Recipe;
import kny.cook.service.PhotoBoardService;
import kny.cook.service.RecipeService;

@WebServlet("/photoboard/list")
public class PhotoBoardListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int recipeNo = Integer.parseInt(request.getParameter("recipeNo"));
    try {

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      RecipeService recipeService = iocContainer.getBean(RecipeService.class);
      PhotoBoardService photoBoardService = iocContainer.getBean(PhotoBoardService.class);
      Recipe recipe = recipeService.get(recipeNo);
      if (recipe == null) {
        throw new Exception("레시피 번호가 유효하지 않습니다.");
      }
      request.setAttribute("recipe", recipe);

      List<PhotoBoard> photoBoards = photoBoardService.listRecipePhoto(recipeNo);
      request.setAttribute("list", photoBoards);

      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/photoboard/list.jsp").include(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list?recipeNo=" + recipeNo);
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
