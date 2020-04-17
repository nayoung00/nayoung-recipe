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
import kny.cook.domain.Member;
import kny.cook.service.MemberService;

@WebServlet("/member/add")
public class MemberAddServlet extends GenericServlet {


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

      MemberService memberService = iocContainer.getBean(MemberService.class);


      Member member = new Member();

      member.setName(req.getParameter("name"));
      member.setEmail(req.getParameter("email"));
      member.setPassword(req.getParameter("password"));
      member.setPhoto(req.getParameter("photo"));
      member.setTel(req.getParameter("tel"));
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
}
