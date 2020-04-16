package kny.cook.servlet;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.PhotoFile;
import kny.cook.domain.Recipe;
import kny.cook.service.PhotoBoardService;
import kny.cook.service.RecipeService;
import kny.cook.util.RequestMapping;

@Component
public class PhotoBoardAddServlet {

  PhotoBoardService photoBoardService;
  RecipeService recipeService;


  public PhotoBoardAddServlet(PhotoBoardService photoBoardService, RecipeService recipeService) {

    this.photoBoardService = photoBoardService;
    this.recipeService = recipeService;
  }

  @RequestMapping("/photoboard/add")
  public void service(Map<String, String> params, PrintWriter out) throws Exception {

    int recipeNo = Integer.parseInt(params.get("RecipeNo"));
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println(
        "<meta http-equiv='refresh' content='2;url=/photoboard/list?recipeNo=" + recipeNo + "'>");
    out.println("<title>사진 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사진 입력 결과</h1>");


    try {
      Recipe recipe = recipeService.get(recipeNo);
      if (recipe == null) {
        throw new Exception("레시피 번호가 유효하지 않습니다.");
      }

      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(params.get("title"));
      photoBoard.setRecipe(recipe);

      ArrayList<PhotoFile> photoFiles = new ArrayList<>();
      for (int i = 1; i <= 5; i++) {
        String filepath = params.get("photo" + i);
        if (filepath.length() > 0) {
          photoFiles.add(new PhotoFile().setFilepath(filepath));
        }
      }
      if (photoFiles.size() == 0) {
        throw new Exception("최소 한 개의 사진 파일을 등록해야 합니다.");
      }
      photoBoard.setFiles(photoFiles);
      photoBoardService.add(photoBoard);

      out.println("<p>새 사진 게시글을 등록했습니다.</p>");
    } catch (Exception e) {
      out.printf("<p>%s</p>\n", e.getMessage());
    }
    out.println("</body>");
    out.println("</html>");
  }
}
