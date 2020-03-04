package kny.cook.servlet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.Recipe;

public class PhotoBoardListServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  RecipeDao recipeDao;

  public PhotoBoardListServlet(PhotoBoardDao photoBoardDao, RecipeDao recipeDao) {
    this.photoBoardDao = photoBoardDao;
    this.recipeDao = recipeDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("레시피 번호?\n!{}!");
    out.flush();


    int recipeNo = Integer.parseInt(in.nextLine());

    Recipe recipe = recipeDao.findByNo(recipeNo);

    if (recipe == null) {
      out.println("레시피 번호가 유효하지 않습니다.");
      return;
    }

    out.printf("요리명: %s\n", recipe.getCook());
    out.println("------------------------------------------------------");

    List<PhotoBoard> photoBoards = photoBoardDao.findAllByRecipeNo(recipeNo);

    for (PhotoBoard photoBoard : photoBoards) {
      out.printf("%d, %s, %s, %d\n", photoBoard.getNo(), photoBoard.getTitle(),
          photoBoard.getCreatedDate(), photoBoard.getViewCount());
    }
  }
}
