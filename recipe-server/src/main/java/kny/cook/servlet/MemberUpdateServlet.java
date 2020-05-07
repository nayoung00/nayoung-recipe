package kny.cook.servlet;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.springframework.context.ApplicationContext;
import kny.cook.domain.Member;
import kny.cook.service.MemberService;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      response.setContentType("text/html;charset=UTF-8");

      ServletContext servletContext = request.getServletContext();
      ApplicationContext iocContainer =
          (ApplicationContext) servletContext.getAttribute("iocContainer");
      MemberService memberService = iocContainer.getBean(MemberService.class);

      Member member = new Member();

      member.setNo(Integer.parseInt(request.getParameter("no")));
      member.setName(request.getParameter("name"));
      member.setEmail(request.getParameter("email"));
      member.setPassword(request.getParameter("password"));
      member.setPhoto(request.getParameter("photo"));
      member.setTel(request.getParameter("tel"));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String dirPath = getServletContext().getRealPath("/upload/member");
        String filename = UUID.randomUUID().toString();
        photoPart.write(dirPath + "/" + filename);
        member.setPassword(filename);
      }

      if (memberService.update(member) > 0) {
        response.sendRedirect("list");
      } else {
        request.getSession().setAttribute("errorMessage", "변경할 회원이 유효하지 않습니다.");
        request.getSession().setAttribute("url", "lesson/list");
        response.sendRedirect("../error");
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.setAttribute("url", "list");
      // 포워드 인쿨르드에서 루트는 현재 웹어플리케이션을 의미한다.
      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}
