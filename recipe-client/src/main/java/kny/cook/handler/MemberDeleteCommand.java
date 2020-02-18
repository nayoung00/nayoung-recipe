package kny.cook.handler;

import kny.cook.dao.MemberDao;
import kny.cook.util.Prompt;

public class MemberDeleteCommand implements Command {

  MemberDao memberDao;
  Prompt prompt;


  public MemberDeleteCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");
      memberDao.delete(no);
      System.out.println("회원을 삭제했습니다.");
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
