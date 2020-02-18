package kny.cook.dao.json;

import java.util.List;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;

public class MemberJsonFileDao extends AbstractJsonFileDao<Member> implements MemberDao {

  public MemberJsonFileDao(String filename) {
    super(filename);
  }

  @Override
  public int insert(Member member) throws Exception {
    if (indexOf(member.getNo()) > -1) {
      return 0;
    }

    list.add(member);
    saveData();
    return 1;
  }

  @Override
  public List<Member> findAll() throws Exception {
    return list;
  }

  @Override
  public Member findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  @Override
  public int update(Member member) throws Exception {
    int index = indexOf(member.getNo());

    if (index == -1) {
      return 0;
    }

    list.set(index, member);
    saveData();
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;

    }
    list.remove(index);
    saveData();
    return 1;

  }

  @Override
  protected <k> int indexOf(k key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key)
        return i;
    }
    return -1;
  }


}
