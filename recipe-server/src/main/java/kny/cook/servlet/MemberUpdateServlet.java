package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;

public class MemberUpdateServlet implements Servlet {

  MemberDao memberDao;

  public MemberUpdateServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Member member = new Member();

    if (memberDao.update(member) > 0) {
      out.println();
    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }

}
