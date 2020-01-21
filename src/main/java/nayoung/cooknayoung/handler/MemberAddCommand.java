package nayoung.cooknayoung.handler;

import java.sql.Date;
import java.util.AbstractList;
import nayoung.cooknayoung.domain.Member;
import nayoung.cooknayoung.util.Prompt;

public class MemberAddCommand implements Command {

  AbstractList<Member> memberList;

  Prompt prompt;

  public MemberAddCommand(Prompt prompt, AbstractList<Member> list) {
    this.prompt = prompt;
    this.memberList = list;
  }

  @Override
  public void execute() {
    Member member = new Member();

    member.setNo(prompt.inputInt("번호? "));
    member.setName(prompt.inputString("이름? "));
    member.setEmail(prompt.inputString("이메일? "));
    member.setPassword(prompt.inputString("암호? "));
    member.setPhoto(prompt.inputString("사진? "));
    member.setTel(prompt.inputString("전화? "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    this.memberList.add(member);

    System.out.println("저장하였습니다.");
  }
}
