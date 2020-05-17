package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    request.getRequestDispatcher("/header").include(request, response);

    out.println("<h1>오류내용</h1>");

    Exception error = (Exception) request.getAttribute("error");

    out.printf("<p>%s</p>", error.getMessage());

    String url = (String) request.getSession().getAttribute("url");
    if (url != null) {
      out.printf("<p><a href-'%s'> 뒤로가기</a></p>", url);
    }
    request.getRequestDispatcher("/footer").include(request, response);
  }
}
