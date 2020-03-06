package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;

public class MemberListServlet implements Servlet {

  MemberDao memberDao;

  public MemberListServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    List<Member> members = memberDao.findAll();

    for (Member member : members) {
      out.printf("%d, %s, %s, %s, %s, %s\n", member.getNo(), member.getName(),
          member.getEmail(), member.getRegisteredDate(), member.getTel(),
          member.getPhoto());
    }
  }
}
