package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import kny.cook.domain.Member;
import kny.cook.service.MemberService;
import kny.cook.util.Prompt;

public class MemberSearchServlet implements Servlet {
  MemberService memberService;

  public MemberSearchServlet(MemberService memberService) {
    this.memberService = memberService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    String keyword = Prompt.getString(in, out, "검색어? ");

    List<Member> members = memberService.search(keyword);
    for (Member member : members) {
      out.printf("%d, %s, %s, %s, %s\n", member.getNo(), member.getName(), member.getEmail(),
          member.getTel(), member.getRegisteredDate());
    }
  }
}
