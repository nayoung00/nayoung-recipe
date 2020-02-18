package kny.cook.handler;

import java.util.List;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;

public class MemberListCommand implements Command {
  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      List<Member> members = memberDao.findAll();

      for (Member member : members) {
        System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", member.getNo(), member.getName(),
            member.getEmail(), member.getPassword(), member.getRegisteredDate(), member.getTel(),
            member.getPhoto());

        System.out.println("member");
      }

    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}
