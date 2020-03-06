package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;
import kny.cook.util.Prompt;

public class MemberSearchServlet implements Servlet {
  MemberDao memberDao;

  public MemberSearchServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    String keyword = Prompt.getString(in, out, "검색어? ");
    
    List<Member> members = memberDao.findByKeWord(keyword);
    for (Member member : members) {
      out.printf("%d, %s, %s, %s, %s\n", member.getNo(), member.getName(), member.getEmail(),
          member.getTel(), member.getRegisteredDate());
    }
  }
}
