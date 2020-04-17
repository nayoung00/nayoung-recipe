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

@WebServlet("/recipe/add")
public class RecipeAddServlet extends GenericServlet {

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

      Recipe recipe = new Recipe();
      recipe.setCook(req.getParameter("cook"));
      recipe.setMaterial(req.getParameter("material"));
      recipe.setMethod(req.getParameter("method"));
      recipe.setExpense(Integer.parseInt(req.getParameter("expense")));
      recipe.setTime(Integer.parseInt(req.getParameter("time")));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println(" <meta charset='UTF-8'>");
      out.println(" <title>레시피 등록</title>");
      out.println(" </head>");
      out.println(" <body>");
      out.println(" <h1>레시피 등록 결과</h1>");
      out.println(" <p>새 레시피를 등록 했습니다.</p>");
      out.println(" </body>");
      out.println(" </html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
