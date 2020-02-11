package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import kny.cook.dao.json.MemberJsonFileDao;
import kny.cook.domain.Member;

public class MemberDetailServlet implements Servlet {

  MemberJsonFileDao memberDao;

  public MemberDetailServlet(MemberJsonFileDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(ObjectInputStream in, ObjectOutputStream out) throws Exception {
    int no = in.readInt();

    Member member = memberDao.findByNo(no);

    if (member != null) {
      out.writeUTF("OK");
    } else {
      out.writeUTF("FAIL");
      out.writeUTF("해당 번호의 회원이 없습니다.");
    }

  }

}
