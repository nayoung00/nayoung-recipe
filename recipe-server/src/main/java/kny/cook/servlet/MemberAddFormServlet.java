package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Member;
import kny.cook.util.RequestMapping;

@Component
public class MemberAddFormServlet {


  @RequestMapping("/member/addForm")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {
    Member member = new Member();

    out.println("<!DOCTYPE html>");
    out.println(" <html>");
    out.println(" <head>");
    out.println("  <meta charset='UTF-8'>");
    out.println(" <title>회원 입력</title>");
    out.println(" </head>");
    out.println(" <body>");
    out.println(" <h1>회원 입력</h1>");
    out.println(" <form action='/member/add'>");
    out.println("  이름:<br>");
    out.println(" <textarea name='name' rows='1' cols='60'></textarea><br>");
    out.println("  이메일:<br>");
    out.println("  <textarea name='email' rows='1' cols='60'></textarea><br>");
    out.println("  비밀번호:<br>");
    out.println(" <textarea name='password' rows='1' cols='60'></textarea><br>");
    out.println(" 사진:<br>");
    out.println(" <textarea name='photo' rows='1' cols='60'></textarea><br>");
    out.println(" 전화번호:<br>");
    out.println(" <textarea name='tel' rows='1' cols='60'></textarea><br>");
    out.println(" <button>제출</button>");
    out.println(" </form>");
    out.println(" </body>");
    out.println(" </html>");
  }
}
