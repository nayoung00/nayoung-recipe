package kny.cook.dao;

import java.util.List;
import java.util.Map;
import kny.cook.domain.Member;

public interface MemberDao {

  int insert(Member member) throws Exception;

  List<Member> findAll() throws Exception;

  Member findByNo(int no) throws Exception;

  default Member findByEmailAndPassword(Map<String, Object> params) throws Exception {
    return null;
  }

  int update(Member member) throws Exception;

  int delete(int no) throws Exception;

  default List<Member> findByKeyword(String keyWord) throws Exception {
    return null;
  }

}
