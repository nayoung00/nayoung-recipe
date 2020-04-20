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
        out.println("<form action='update'>");
        out.printf("번호: <input name='no' readonly type='text' value='%d'><br>\n", recipe.getNo());
        out.printf("요리: <input name='cook' readonly type='text' value='%s'><br>\n",
            recipe.getCook());
        out.printf("재료: <input name='material' readonly type='text' value='%s'><br>\n",
            recipe.getMaterial());
        out.printf("방법<br>");
        out.printf(" <textarea name='method' rows='5' cols='60'>%s</textarea><br>\n",
            recipe.getMethod());
        out.printf("비용: <input name='expense' readonly type='text' value='%d'><br>\n",
            recipe.getExpense());
        out.printf("시간: <input name='time' readonly type='text' value='%d'><br>\n",
            recipe.getTime());
        out.println("<p>");
        out.println("<button>변경</button>");
        out.printf("<a href='delete?no=%d'>삭제</a>\n", recipe.getNo());
        out.printf("<a href='../photoboard/list?recipeNo=%d'>사진게시판</a>\n", recipe.getNo());
        out.println("</p>");
        out.println("</form>");
      } else {
        out.println("<p>해당 번호의 강의가 없습니다.</p>");
      }
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
