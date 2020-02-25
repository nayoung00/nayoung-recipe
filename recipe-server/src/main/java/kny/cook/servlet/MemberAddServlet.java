package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;

public class MemberAddServlet implements Servlet {

  MemberDao memberDao;

  public MemberAddServlet(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    Member member = new Member();

    out.println("회원? \n!{}!");
    member.setName(in.nextLine());
    out.println("이메일? \n!{}!");
    member.setEmail(in.nextLine());
    out.println("암호? \n!{}!");
    member.setPassword(in.nextLine());
    out.println("사진? \n!{}!");
    member.setPhoto(in.nextLine());
    out.println("전화? \n!{}!");
    member.setTel(in.nextLine());

    out.flush();
    if (memberDao.insert(member) > 0) {
      out.println("새 회원을 등록 했습니다.");
    } else {
      out.println("새 회원을 등록 했습니다.");
    }
  }
}
