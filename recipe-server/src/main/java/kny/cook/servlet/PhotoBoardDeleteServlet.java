package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.service.PhotoBoardService;
import kny.cook.util.RequestMapping;

@Component
public class PhotoBoardDeleteServlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @RequestMapping("/photoboard/delete")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='2;url=/photoboard/list?recipeNo=%d'>\n", //
        Integer.parseInt(params.get("recipeNo")));
    out.println("<title>사진 삭제</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사진 삭제 결과</h1>");

    int no = Integer.parseInt(params.get("no"));
    try {
      photoBoardService.delete(no);
      out.println("<p>사진을 삭제했습니다.</p>");
    } catch (Exception e) {
      out.println("<p>사진 삭제에 실패했습니다.</p>");
    }

    out.println("</body>");
    out.println("</html>");

  }
}
