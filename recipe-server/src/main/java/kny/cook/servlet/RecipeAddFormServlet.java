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

@WebServlet("/recipe/addForm")
public class RecipeAddFormServlet extends GenericServlet {

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

      out.println("<!DOCTYPE html>");
      out.println(" <html>");
      out.println(" <head>");
      out.println(" <meta charset='UTF-8'>");
      out.println(" <title>레시피 입력</title>");
      out.println(" </head>");
      out.println(" <body>");
      out.println(" <h1>레시피 입력</h1>");
      out.println(" <form action='/recipe/add'>");
      out.println(" 요리:<br>");
      out.println(" <textarea name='cook' rows='1' cols='60'></textarea><br>");
      out.println(" 재료:<br>");
      out.println(" <textarea name='material' rows='1' cols='60'></textarea><br>");
      out.println(" 방법:<br>");
      out.println(" <textarea name='method' rows='1' cols='60'></textarea><br>");
      out.println(" 비용:<br>");
      out.println(" <textarea name='expense' rows='1' cols='60'></textarea><br>");
      out.println(" 시간:<br>");
      out.println(" <textarea name='time' rows='1' cols='60'></textarea><br>");
      out.println(" <button>제출</button>");
      out.println(" </form>");
      out.println(" </body>");
      out.println(" </html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
