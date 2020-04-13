package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Member;
import kny.cook.service.MemberService;
import kny.cook.util.RequestMapping;

@Component
public class MemberAddServlet {

  MemberService memberService;

  public MemberAddServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/add")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    Member member = new Member();

    member.setName(params.get("name"));
    member.setEmail(params.get("email"));
    member.setPassword(params.get("password"));
    member.setPhoto(params.get("photo"));
    member.setTel(params.get("tel"));
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
  }
}
