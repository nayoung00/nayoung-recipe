package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.domain.Member;
import kny.cook.service.MemberService;
import kny.cook.util.Component;
import kny.cook.util.Prompt;
import kny.cook.util.RequestMapping;

@Component
public class LoginServlet {
  MemberService memberService;

  public LoginServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/auth/login")
  public void service(Scanner in, PrintStream out) throws Exception {
    String email = Prompt.getString(in, out, "email? ");
    String password = Prompt.getString(in, out, "password? ");

    Member member = memberService.findByEmailAndPassword(email, password);

    if (member != null) {
      out.printf("'%s'님 환영합니다.\n", member.getName());
    } else {
      out.println("사용자가 정보가 유효하지 않습니다.");
    }
  }
}
