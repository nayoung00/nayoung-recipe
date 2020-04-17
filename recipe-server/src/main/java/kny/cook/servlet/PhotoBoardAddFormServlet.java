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

@WebServlet("/photoboard/addForm")
public class PhotoBoardAddFormServlet extends GenericServlet {
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

      int recipeNo = Integer.parseInt(req.getParameter("RecipeNo"));
      Recipe recipe = recipeService.get(recipeNo);
      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>사진 입력</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>사진 입력</h1>");
      out.println("<form action='/photoboard/add'>");
      out.printf("레시피번호: <input name='recipeNo' type='text' value='%d' readonly><br>\n", //
          recipe.getNo());
      out.printf("요리명: %s<br>\n", recipe.getCook());
      out.println("내용:<br>");
      out.println("<textarea name='title' rows='5' cols='60'></textarea><br>");
      out.println("<hr>");
      out.println("사진: <input name='photo1' type='file'><br>");
      out.println("사진: <input name='photo2' type='file'><br>");
      out.println("사진: <input name='photo3' type='file'><br>");
      out.println("사진: <input name='photo4' type='file'><br>");
      out.println("사진: <input name='photo5' type='file'><br>");
      out.println("<button>제출</button>");
      out.println("</form>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
