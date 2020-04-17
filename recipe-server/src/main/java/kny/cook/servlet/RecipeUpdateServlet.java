package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;

@WebServlet("/recipe/update")
public class RecipeUpdateServlet extends GenericServlet {


  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      ServletContext servletContext = req.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      RecipeService recipeService = iocContainer.getBean(RecipeService.class);

      Recipe recipe = new Recipe();

      recipe.setNo(Integer.parseInt(req.getParameter("no")));
      recipe.setCook(req.getParameter("name"));
      recipe.setMaterial(req.getParameter("material"));
      recipe.setMethod(req.getParameter("method"));
      recipe.setExpense(Integer.parseInt(req.getParameter("expense")));
      recipe.setTime(Integer.parseInt(req.getParameter("time")));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='1;url=/recipe/list'>");
      out.println("<title>레시피 변경</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>레시피 변경 결과</h1>");

      if (recipeService.update(recipe) > 0) {
        out.println("<p>레시피를 변경했습니다.</p>");
      } else {
        out.println("<p>레시피 변경에 실패했습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
