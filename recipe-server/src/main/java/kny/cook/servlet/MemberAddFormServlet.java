package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/member/addForm")
public class MemberAddFormServlet extends GenericServlet {

  private static final long serialVersionUID = 1L;

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {
    try {
      res.setContentType("text/html;charset=UTF-8");
      PrintWriter out = res.getWriter();

      out.println("<!DOCTYPE html>");
      out.println(" <html>");
      out.println(" <head>");
      out.println("  <meta charset='UTF-8'>");
      out.println(" <title>회원 입력</title>");
      out.println(" </head>");
      out.println(" <body>");
      out.println(" <h1>회원 입력</h1>");
      out.println(" <form action='/member/add'>");
      out.println("  이름:<br>");
      out.println(" <textarea name='name' rows='1' cols='60'></textarea><br>");
      out.println("  이메일:<br>");
      out.println("  <textarea name='email' rows='1' cols='60'></textarea><br>");
      out.println("  비밀번호:<br>");
      out.println(" <textarea name='password' rows='1' cols='60'></textarea><br>");
      out.println(" 사진:<br>");
      out.println(" <textarea name='photo' rows='1' cols='60'></textarea><br>");
      out.println(" 전화번호:<br>");
      out.println(" <textarea name='tel' rows='1' cols='60'></textarea><br>");
      out.println(" <button>제출</button>");
      out.println(" </form>");
      out.println(" </body>");
      out.println(" </html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
