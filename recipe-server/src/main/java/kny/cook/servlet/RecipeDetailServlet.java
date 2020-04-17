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

@WebServlet("/recipe/detail")
public class RecipeDetailServlet extends GenericServlet {


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

      int no = Integer.parseInt(req.getParameter("no"));
      Recipe recipe = recipeService.get(no);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println(" <head>");
      out.println("<meta charset='UTF-8'>");
      out.println(" <title>레시피 상세정보</title>");
      out.println(" </head>");
      out.println(" <body>");
      out.println(" <h1> 레시피 상세정보</h1>");

      if (recipe != null) {
        out.printf("번호: %d<br>\n", recipe.getNo());
        out.printf("요리: %s<br>\n", recipe.getCook());
        out.printf("재료: %s<br>\n", recipe.getMaterial());
        out.printf("방법: %s<br>\n", recipe.getMethod());
        out.printf("비용: %d<br>\n", recipe.getExpense());
        out.printf("시간: %d<br>\n", recipe.getTime());
        out.printf("<p><a href='/recipe/delete?no=%d'삭제</a>\n", recipe.getNo());
        out.printf("<a href='/recipe/updateForm?no=%d'>변경</a></p>\n", recipe.getNo());

      } else {
        out.println("<p>해당 번호의 레시피가 없습니다.</p>");
      }
      out.println(" </body>");
      out.println(" </html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
