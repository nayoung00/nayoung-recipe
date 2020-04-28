package kny.cook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.Member;
import kny.cook.service.MemberService;

@WebServlet("/member/list")
public class MemberListServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse respons)
      throws ServletException, IOException {

    try {
      respons.setContentType("text/html;charset=UTF-8");
      PrintWriter out = respons.getWriter();

      ServletContext servletContext = getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);


      out.println("<!DOCTYPE html>");
      out.println("<html>");
      out.println("<head>");
      out.println("<meta charset='UTF-8'>");
      out.println("<title>회원 목록</title>");
      out.println("</head>");
      out.println("<body>");
      out.println("<h1>회원</h1>");
      out.println("<a href='add'>새 회원</a><br>");
      out.println("<table border='1'>");
      out.println("<tr>");
      out.println("<th>번호</th>");
      out.println("<th>이름</th>");
      out.println("<th>이메일</th>");
      out.println("<th>전화</th>");
      out.println("<th>등록일</th>");
      out.println("</tr>");

      List<Member> members = memberService.list();

      for (Member member : members) {
        out.printf(
            "<tr><td>%d</td>, <td><a href='detail?no=%d'>%s</a></td>, <td>%s</td>, <td>%s</td>, <td>%s</td></tr>\n",
            member.getNo(), member.getNo(), member.getName(), member.getEmail(), member.getTel(),
            member.getRegisteredDate());
      }
      out.println("</table>");

      out.println("<hr>");
      out.println("<form action='search' method='get'>");
      out.println("검색어: <input name='keyword' type='text'>");
      out.println("<button>검색</button>");
      out.println("</body>");
      out.println("</html>");
    } catch (Exception e) {
      throw new ServletException(e);
    }
  }
}
