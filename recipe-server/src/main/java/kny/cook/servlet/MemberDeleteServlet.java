package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.MemberDao;
import kny.cook.util.Prompt;

public class MemberDeleteServlet implements Servlet {

  MemberDao memberDao;

  public MemberDeleteServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    if (memberDao.delete(no) > 0) {
      out.println("회원을 삭제했습니다.");
    } else {
      out.println("해당 번호의 회원이 없습니다.");
    }
  }

}
