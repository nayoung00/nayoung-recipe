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
  public int add(Member member) throws Exception {
    return memberDao.insert(member);
  }

  @Override
  public int delete(int no) throws Exception {
    return memberDao.delete(no);
  }

  @Override
  public List<Member> search(String keyword) throws Exception {
    return memberDao.findByKeyword(keyword);
  }

  @Override
  public int update(Member member) throws Exception {
    return memberDao.update(member);
  }

  @Override
  public Member findByEmailAndPassword(String email, String password) throws Exception {
    return memberDao.findByEmailAndPassword(email, password);
  }

  @Override
  public Member findByNo(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

}
