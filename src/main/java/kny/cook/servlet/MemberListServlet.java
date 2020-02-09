package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.domain.Member;

public class MemberListServlet implements Servlet {

  List<Member> members;

  public MemberListServlet(List<Member> members) {
    this.members = members;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    out.writeUTF("OK");
    out.reset();
    out.writeObject(members);


  }

}
