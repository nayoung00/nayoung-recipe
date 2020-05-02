package kny.cook.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;

@WebServlet("/recipe/update")
public class RecipeUpdateServlet extends HttpServlet {


  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      RecipeService recipeService = iocContainer.getBean(RecipeService.class);

      Recipe recipe = new Recipe();

      recipe.setNo(Integer.parseInt(request.getParameter("no")));
      recipe.setCook(request.getParameter("name"));
      recipe.setMaterial(request.getParameter("material"));
      recipe.setMethod(request.getParameter("method"));
      recipe.setExpense(Integer.parseInt(request.getParameter("expense")));
      recipe.setTime(Integer.parseInt(request.getParameter("time")));

      if (recipeService.update(recipe) > 0) {
        response.sendRedirect("list");
      } else {
        request.getSession().setAttribute("errorMessage", "레시피를 변경할 수 없습니다.");
        request.getSession().setAttribute("url", "recipe/list");
        response.sendRedirect("../error");
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
