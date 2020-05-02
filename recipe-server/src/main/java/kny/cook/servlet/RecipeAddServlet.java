package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.Recipe;
import kny.cook.service.RecipeService;

@WebServlet("/recipe/add")
public class RecipeAddServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println(" <html>");
      out.println(" <head>");
      out.println(" <meta charset='UTF-8'>");
      out.println(" <title>레시피 입력</title>");
      out.println(" </head>");
      out.println(" <body>");
      out.println(" <h1>레시피 입력</h1>");
      out.println(" <form action='add' method='post'>");
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


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      request.setCharacterEncoding("UTF-8");

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      RecipeService recipeService = iocContainer.getBean(RecipeService.class);


      Recipe recipe = new Recipe();
      recipe.setCook(request.getParameter("cook"));
      recipe.setMaterial(request.getParameter("material"));
      recipe.setMethod(request.getParameter("method"));
      recipe.setExpense(Integer.parseInt(request.getParameter("expense")));
      recipe.setTime(Integer.parseInt(request.getParameter("time")));

      recipeService.add(recipe);

      if (recipeService.add(recipe) > 0) {
        response.sendRedirect("list");
      } else {
        request.getSession().setAttribute("errorMessage", "레시피를 추가할 수 없습니다.");
        request.getSession().setAttribute("url", "recipe/list");
        response.sendRedirect("../error");
      }

    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
