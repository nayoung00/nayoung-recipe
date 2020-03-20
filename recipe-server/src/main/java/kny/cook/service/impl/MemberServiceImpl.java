package kny.cook.service.impl;

import java.util.List;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;
import kny.cook.service.MemberService;

public class MemberServiceImpl implements MemberService {

  MemberDao memberDao;

  public MemberServiceImpl(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public int insert(Member member) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete(int no) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Member findByNo(int no) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Member> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Member> findByKeyword(String keyword) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int update(Member member) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) {
    // TODO Auto-generated method stub
    return null;
  }

}
