package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Map;
import org.springframework.stereotype.Component;
import kny.cook.domain.Recipe;
import kny.cook.service.PhotoBoardService;
import kny.cook.service.RecipeService;
import kny.cook.util.RequestMapping;

@Component
public class PhotoBoardAddFormServlet {

  PhotoBoardService photoBoardService;
  RecipeService recipeService;


  public PhotoBoardAddFormServlet(PhotoBoardService photoBoardService,
      RecipeService recipeService) {

    this.photoBoardService = photoBoardService;
    this.recipeService = recipeService;
  }

  @RequestMapping("/photoboard/addForm")
  public void service(Map<String, String> params, PrintStream out) throws Exception {
    int recipeNo = Integer.parseInt(params.get("RecipeNo"));
    Recipe recipe = recipeService.get(recipeNo);
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>사진 입력</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>사진 입력</h1>");
    out.println("<form action='/photoboard/add'>");
    out.printf("레시피번호: <input name='recipeNo' type='text' value='%d' readonly><br>\n", //
        recipe.getNo());
    out.printf("요리명: %s<br>\n", recipe.getCook());
    out.println("내용:<br>");
    out.println("<textarea name='title' rows='5' cols='60'></textarea><br>");
    out.println("<hr>");
    out.println("사진: <input name='photo1' type='file'><br>");
    out.println("사진: <input name='photo2' type='file'><br>");
    out.println("사진: <input name='photo3' type='file'><br>");
    out.println("사진: <input name='photo4' type='file'><br>");
    out.println("사진: <input name='photo5' type='file'><br>");
    out.println("<button>제출</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}
