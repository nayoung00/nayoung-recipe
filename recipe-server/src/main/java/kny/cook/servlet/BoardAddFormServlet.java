package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.util.RequestMapping;

@Component
public class BoardAddFormServlet {

  @RequestMapping("/board/addForm")
  public void service(Map<String, String> parmas, PrintWriter out) throws Exception {

    out.println(" <!DOCTYPE html>");
    out.println(" <html>");
    out.println(" <head>");
    out.println(" <meta charset='UTF-8'>");
    out.println(" <title>게시물 입력</title>");
    out.println(" </head>");
    out.println(" <body>");
    out.println(" <h1>게시물 입력<h1>");
    out.println("<form action = '/board/add'>");
    out.println("  내용: <br>");
    out.println("   <textarea name='title' rows='7' cols='60'></textarea><br>");
    out.println("   <button>제출</button>");
    out.println(" </form>");
    out.println("</body>");
    out.println("</html>");
  }
}
