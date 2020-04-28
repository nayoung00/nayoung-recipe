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
import kny.cook.domain.Member;
import kny.cook.service.MemberService;

@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");

      MemberService memberService = iocContainer.getBean(MemberService.class);

      Member member = new Member();

      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPhoto(request.getParameter("photo"));
      member.setTel(request.getParameter("tel"));
      memberService.add(member);

      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println(" <meta charset='UTF-8'>");
      out.println(" <title>회원 등록</title>");
      out.println(" </head>");
      out.println(" <body>");
      out.println(" <h1>회원 등록 결과</h1>");
      out.println(" <p>새 회원을 등록 했습니다.</p>");
      out.println(" </body>");
      out.println(" </html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }

  }


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter();

      out.println("<!DOCTYPE html>");
      out.println(" <html>");
      out.println(" <head>");
      out.println("  <meta charset='UTF-8'>");
      out.println("<meta http-equiv='refresh' content='2;url=list'>");
      out.println(" <title>회원 입력</title>");
      out.println(" </head>");
      out.println(" <body>");
      out.println(" <h1>회원 입력</h1>");
      out.println(" <form action='add'>");
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
