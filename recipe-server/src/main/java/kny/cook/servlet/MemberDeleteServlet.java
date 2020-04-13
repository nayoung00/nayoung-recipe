package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.service.MemberService;
import kny.cook.util.RequestMapping;

@Component
public class MemberDeleteServlet {

  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }



  @RequestMapping("/member/delete")
  public void service(Map<String, String> params, PrintStream out) throws Exception {

    out.println("");
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<meta http-equiv='refresh' content='2;url=/member/list'>");
    out.println(" <title>회원 삭제</title>");
    out.println(" </head>");
    out.println("<body>");
    out.println(" <h1>회원 삭제 결과</h1>");

    int no = Integer.parseInt(params.get("no"));

    if (memberService.delete(no) > 0) {
      out.println("<p>회원을 삭제했습니다.</p>");
    } else {
      out.println("<p>해당 번호의 회원이 없습니다.</p>");
    }
    out.println("</body>");
    out.println("</html>");
  }

}
