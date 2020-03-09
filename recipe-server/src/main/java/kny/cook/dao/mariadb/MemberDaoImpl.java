package kny.cook.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import kny.cook.dao.MemberDao;
import kny.cook.domain.Member;
import kny.cook.sql.DataSource;

public class MemberDaoImpl implements MemberDao {

  DataSource dataSource;

  public MemberDaoImpl(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  @Override
  public int insert(Member member) throws Exception {

    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("insert into rms_member(name, email, pwd, tel, photo) "
                + "values(?,?,password(?),?,?)")) {

      stmt.setString(1, member.getName());
      stmt.setString(2, member.getEmail());
      stmt.setString(3, member.getPassword());
      stmt.setString(4, member.getTel());
      stmt.setString(5, member.getPhoto());

      return stmt.executeUpdate();
    }
  }

  @Override
  public List<Member> findAll() throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select member_id, name, email, tel, cdt from rms_member");
        ResultSet rs = stmt.executeQuery()) {
      ArrayList<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member member = new Member();
        member.setNo(rs.getInt("member_id"));
        member.setName(rs.getString("name"));
        member.setEmail(rs.getString("email"));
        member.setRegisteredDate(rs.getDate("cdt"));
        member.setTel(rs.getString("tel"));

        list.add(member);
      }
      return list;
    }
  }

  @Override
  public Member findByNo(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select member_id, name, email, pwd, tel, photo"
                + " from rms_member" + " where member_id=?")) {

      stmt.setInt(1, no);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member member = new Member();

          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setRegisteredDate(rs.getDate("cdt"));
          member.setTel(rs.getString("tel"));
          member.setPhoto(rs.getString("photo"));
          return member;

        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Member member) throws Exception {

    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "update rms_member set name= ?, email=?, pwd=password(?), tel=?, photo=? where member_id=?")) {

      stmt.setString(1, member.getName());
      stmt.setString(3, member.getEmail());
      stmt.setString(2, member.getPassword());
      stmt.setString(5, member.getTel());
      stmt.setString(4, member.getPhoto());
      stmt.setInt(4, member.getNo());

      int result = stmt.executeUpdate();
      return result;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("delete from rms_member where member_id=?")) {
      stmt.setInt(1, no);
      int result = stmt.executeUpdate();
      return result;
    }
  }

  @Override
  public List<Member> findByKeWord(String keyword) throws Exception {

    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("select member_id, name, email, tel, cdt"
            + " from rms_member where name like ? or email like ? or tel like ?")) {
      String value = "%" + keyword + "%";

      stmt.setString(1, value);
      stmt.setString(2, value);
      stmt.setString(3, value);

      try (ResultSet rs = stmt.executeQuery()) {

        ArrayList<Member> list = new ArrayList<>();

        while (rs.next()) {
          Member member = new Member();

          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setTel(rs.getString("tel"));
          member.setRegisteredDate(rs.getDate("cdt"));

          list.add(member);

        }
        return list;
      }
    }
  }

  @Override
  public Member findByEmailandPassword(String email, String password) throws Exception {

    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt =
            con.prepareStatement("select member_id, name, email, pwd, tel, photo"
                + " from rms_member" + " where email=? and pwd=password(?)")) {

      stmt.setString(1, email);
      stmt.setString(2, password);

      try (ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setPassword(rs.getString("pwd"));
          member.setTel(rs.getString("tel"));
          member.setPhoto(rs.getString("photo"));

          return member;

        } else {
          return null;
        }
      }
    }
  }
}
