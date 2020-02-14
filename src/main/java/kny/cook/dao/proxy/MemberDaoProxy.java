package kny.cook.dao.proxy;

import java.util.List;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;

public class MemberDaoProxy implements MemberDao {

  DaoProxyHelper daoProxyHelper;
  private Worker worker;

  public MemberDaoProxy(DaoProxyHelper daoProxyHelper) {
    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Member member) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    return (List<Member>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<Member>) in.readObject();
    });
  }

  @Override
  public Member findByNo(int no) throws Exception {
    return (Member) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (Member) in.readObject();
    });
  }

  @Override
  public int update(Member member) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/update");
      out.writeObject(member);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/member/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }
}

