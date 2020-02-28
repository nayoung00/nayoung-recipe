package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.Recipe;

public class PhotoBoardAddServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardAddServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    PhotoBoard photoBoard = new PhotoBoard();

    out.println("제목? \n!{}!");
    out.flush();
    photoBoard.setTitle(in.nextLine());

    out.println("레시피 번호? \n!{}!");
    out.flush();

    Recipe recipe = new Recipe();
    recipe.setNo(Integer.parseInt(in.nextLine()));

    photoBoard.setRecipe(recipe);

    if (photoBoardDao.insert(photoBoard) > 0) {
      out.println("새 사진 게시글을 등록했습니다.");
    } else {
      out.println("사진 게시글 등록에 실패했습니다.");
    }
  }
}
