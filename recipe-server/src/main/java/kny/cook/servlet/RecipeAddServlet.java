package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kny.cook.domain.Recipe;

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
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println(" <title>레시피 입력</title>");
      out.println(" </head>");
      out.println(" <body>");
      out.println(" <h1>레시피 입력</h1>");
      out.println(" <form action='add'>");
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
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      Recipe recipe = new Recipe();
      recipe.setCook(request.getParameter("cook"));
      recipe.setMaterial(request.getParameter("material"));
      recipe.setMethod(request.getParameter("method"));
      recipe.setExpense(Integer.parseInt(request.getParameter("expense")));
      recipe.setTime(Integer.parseInt(request.getParameter("time")));

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println(" <meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
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
