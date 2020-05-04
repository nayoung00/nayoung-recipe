package kny.cook.servlet;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import kny.cook.service.RecipeService;

@WebServlet("/recipe/delete")
public class RecipeDeleteServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      RecipeService recipeService = iocContainer.getBean(RecipeService.class);

      int no = Integer.parseInt(request.getParameter("no"));


      if (recipeService.delete(no) > 0) {
        response.sendRedirect("list");
      } else {
        request.getSession().setAttribute("errorMessage", "레시피를 삭제할 수 없습니다.");
        request.getSession().setAttribute("url", "recipe/list");
        response.sendRedirect("../error");
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      // 포워드 인쿨르드에서 루트는 현재 웹어플리케이션을 의미한다.
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}

