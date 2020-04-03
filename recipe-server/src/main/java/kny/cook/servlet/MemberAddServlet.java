package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.domain.Member;
import kny.cook.service.MemberService;
import kny.cook.util.Component;
import kny.cook.util.Prompt;
import kny.cook.util.RequestMapping;

@Component
public class MemberAddServlet {

  MemberService memberService;

  public MemberAddServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @RequestMapping("/member/add")
  public void service(Scanner in, PrintStream out) throws Exception {
    Member member = new Member();

    member.setName(Prompt.getString(in, out, "이름? "));
    member.setEmail(Prompt.getString(in, out, "이메일? "));
    member.setPassword(Prompt.getString(in, out, "암호? "));
    member.setPhoto(Prompt.getString(in, out, "사진? "));
    member.setTel(Prompt.getString(in, out, "전화? "));

    out.flush();
    if (memberService.add(member) > 0) {
      out.println("새 회원을 등록 했습니다.");
    } else {
      out.println("새 회원을 등록 했습니다.");
    }
  }
}
