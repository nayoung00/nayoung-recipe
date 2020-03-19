package kny.cook.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.PhotoFileDao;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.PhotoFile;
import kny.cook.domain.Recipe;
import kny.cook.sql.PlatformTransactionManager;
import kny.cook.sql.TransactionCallback;
import kny.cook.sql.TransactionTemplate;
import kny.cook.util.Prompt;

public class PhotoBoardAddServlet implements Servlet {

  TransactionTemplate transactionTemplate;
  PhotoBoardDao photoBoardDao;
  RecipeDao recipeDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardAddServlet(PlatformTransactionManager txManager, PhotoBoardDao photoBoardDao,
      RecipeDao recipeDao, PhotoFileDao photoFileDao) {

    this.transactionTemplate = new TransactionTemplate(txManager);
    this.photoBoardDao = photoBoardDao;
    this.recipeDao = recipeDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목? "));

    int recipeNo = Prompt.getInt(in, out, "레시피 번호? ");

    Recipe recipe = recipeDao.findByNo(recipeNo);
    if (recipe == null) {
      out.println("레시피 번호가 유효하지 않습니다.");
      return;
    }

    photoBoard.setRecipe(recipe);

    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);

    transactionTemplate.execute(new TransactionCallback() {

      @Override
      public Object doInTransaction() throws Exception {

        if (photoBoardDao.insert(photoBoard) == 0) {
          throw new Exception("사진 게시글 등록에 실패했습니다.");

        }
        photoFileDao.insert(photoBoard);
        out.println("새 사진 게시글을 등록했습니다.");
        return null;
      }
    });
  }

  private List<PhotoFile> inputPhotoFiles(Scanner in, PrintStream out) {

    out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
    out.println("파일명 입력 없이 그냥 엔터를 치면 파일을 추가를 마칩니다.");

    ArrayList<PhotoFile> photoFiles = new ArrayList<>();

    while (true) {
      String filepath = Prompt.getString(in, out, "사진 파일? ");

      if (filepath.length() == 0) {
        if (photoFiles.size() > 0) {
          break;
        } else {
          out.println("최소 한 개의 사진 파일을 등록해야 합니다.");
          continue;
        }
      }
      photoFiles.add(new PhotoFile().setFilepath(filepath));
    }
    return photoFiles;
  }
}
