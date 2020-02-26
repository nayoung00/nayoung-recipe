package kny.cook.dao;

import java.util.List;
import kny.cook.domain.Member;

public interface MemberDao {

  public int insert(Member member) throws Exception;

  public List<Member> findAll() throws Exception;

  public Member findByNo(int no) throws Exception;

  public int update(Member member) throws Exception;

  public int delete(int no) throws Exception;

  default List<Member> findByKeWord(String keyWord) throws Exception {
    return null;
  }
}
