package kny.cook.handler;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import kny.cook.dao.MemberDao;

public class MemberListCommand implements Command {

  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      Class.forName("org.mariadb.jdbc.Driver");

      Connection con =
          DriverManager.getConnection("jdbc:mariadb://localhost:3306/recipedb", "study", "1111");

      Statement stmt = con.createStatement();

      ResultSet rs =
          stmt.executeQuery("select member_id, name, email, pwd, cdt, tel, photo from rms_member");

      while (rs.next()) {
        System.out.printf("%d, %s, %s, %s, %s, %d, %s\n", rs.getInt("member_id"),
            rs.getString("name"), rs.getString("email"), rs.getString("pwd"), rs.getDate("cdt"),
            rs.getInt("tel"), rs.getString("photo"));
      }
    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}
