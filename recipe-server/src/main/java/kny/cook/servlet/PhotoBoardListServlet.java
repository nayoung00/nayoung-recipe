package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.Recipe;
import kny.cook.service.PhotoBoardService;
import kny.cook.service.RecipeService;
import kny.cook.util.Prompt;
import kny.cook.util.RequestMapping;


@Component
public class PhotoBoardListServlet {

  PhotoBoardService photoBoardService;
  RecipeService recipeService;

  public PhotoBoardListServlet(PhotoBoardService photoBoardService, RecipeService recipeService) {
    this.photoBoardService = photoBoardService;
    this.recipeService = recipeService;
  }

  @RequestMapping("/photoboard/list")
  public void service(Scanner in, PrintStream out) throws Exception {

    int recipeNo = Prompt.getInt(in, out, "레시피 번호? ");

    Recipe recipe = recipeService.findByNo(recipeNo);

    if (recipe == null) {
      out.println("레시피 번호가 유효하지 않습니다.");
      return;
    }

    out.printf("요리명: %s\n", recipe.getCook());
    out.println("------------------------------------------------------");

    List<PhotoBoard> photoBoards = photoBoardService.listRecipePhoto(recipeNo);

    for (PhotoBoard photoBoard : photoBoards) {
      out.printf("%d, %s, %s, %d\n", photoBoard.getNo(), photoBoard.getTitle(),
          photoBoard.getCreatedDate(), photoBoard.getViewCount());
    }
  }
}
