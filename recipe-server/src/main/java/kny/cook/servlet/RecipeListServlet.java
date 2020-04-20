package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;

@WebServlet("/recipe/list")
public class RecipeListServlet extends GenericServlet {


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

      List<Recipe> recipes = recipeService.findAll();

      out.println(" <!DOCTYPE html>");
      out.println("<html>");
      out.println(" <head>");
      out.println(" <meta charset='UTF-8'>");
      out.println("  <title>레시피 목록</title>");
      out.println(" <body>");
      out.println("  <h1> 레시피 </h1>");
      out.println("  </body>");
      out.println("  <a href='addForm'> 새 글 </a><br>");
      out.println("  <table border='14'>");
      out.println("  <tr>");
      out.println(" <th>번호</th>");
      out.println("  <th>요리</th>");
      out.println(" <th>재료</th>");
      out.println(" <th>방법</th>");
      out.println(" <th>비용</th>");
      out.println(" <th>시간</th>");
      out.println(" </tr>");
      for (Recipe recipe : recipes) {
        out.printf(
            "<tr><td>%d</td>, <td><a href='detail?no=%d'>%s</a></td>, <td>%s</td>, <td>%s</td>, <td>%d</td>, <td>%d</td></tr>\n",
            recipe.getNo(), recipe.getNo(), recipe.getCook(), recipe.getMaterial(),
            recipe.getMethod(), recipe.getExpense(), recipe.getTime());
      }
      out.println("</head>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
