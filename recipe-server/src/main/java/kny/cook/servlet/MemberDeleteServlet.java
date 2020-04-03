package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.service.MemberService;
import kny.cook.util.Component;
import kny.cook.util.Prompt;

@Component("/member/delete")
public class MemberDeleteServlet implements Servlet {

  MemberService memberService;

  public MemberDeleteServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    if (memberService.delete(no) > 0) {
      out.println("회원을 삭제했습니다.");
    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }

}
