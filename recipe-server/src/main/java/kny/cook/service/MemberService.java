package kny.cook.service;

import java.util.List;
import kny.cook.domain.Member;

public interface MemberService {

  int insert(Member member);

  int delete(int no);

  Member findByNo(int no);

  List<Member> findAll();

  List<Member> findByKeyword(String keyword);

  int update(Member member);

  Member findByEmailAndPassword(String email, String password);

}
