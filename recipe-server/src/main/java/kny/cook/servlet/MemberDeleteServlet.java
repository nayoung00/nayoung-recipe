package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.MemberDao;

public class MemberDeleteServlet implements Servlet {

  MemberDao memberDao;

  public MemberDeleteServlet(MemberDao memberDao) {

    this.memberDao = memberDao;
  }


  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    if (memberDao.delete(no) > 0) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }
  }

}
