package kny.cook.servlet;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.PhotoFile;
import kny.cook.domain.Recipe;
import kny.cook.service.PhotoBoardService;
import kny.cook.service.RecipeService;
import kny.cook.util.Prompt;
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
  public void service(Scanner in, PrintStream out) throws Exception {

    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(Prompt.getString(in, out, "제목? "));

    int recipeNo = Prompt.getInt(in, out, "레시피 번호? ");

    Recipe recipe = recipeService.findByNo(recipeNo);
    if (recipe == null) {
      out.println("레시피 번호가 유효하지 않습니다.");
      return;
    }
    photoBoard.setRecipe(recipe);

    List<PhotoFile> photoFiles = inputPhotoFiles(in, out);
    photoBoard.setFiles(photoFiles);

    photoBoardService.add(photoBoard);
    out.println("새 사진 게시글을 등록 했습니다.");
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
