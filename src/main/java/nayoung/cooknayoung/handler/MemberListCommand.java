package nayoung.cooknayoung.handler;

import java.util.AbstractList;
import java.util.Iterator;
import nayoung.cooknayoung.domain.Member;

public class MemberListCommand implements Command {

  AbstractList<Member> memberList;


  public MemberListCommand(AbstractList<Member> list) {
    this.memberList = list;
  }

  @Override
  public void execute() {
    Iterator<Member> iterator = memberList.iterator();
    while (iterator.hasNext()) {
      Member m = iterator.next();

      System.out.printf("%d, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(), m.getTel(),
          m.getRegisteredDate());
    }
  }
}
