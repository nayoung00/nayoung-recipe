package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.domain.PhotoBoard;
import kny.cook.domain.PhotoFile;
import kny.cook.service.PhotoBoardService;
import kny.cook.util.Prompt;

public class PhotoBoardDetailServlet implements Servlet {

  PhotoBoardService photoBoardService;


  public PhotoBoardDetailServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    int no = Prompt.getInt(in, out, "번호? ");

    PhotoBoard photoBoard = photoBoardService.findByNo(no);

    if (photoBoard != null) {
      out.printf("번호: %d\n", photoBoard.getNo());
      out.printf("제목: %s\n", photoBoard.getTitle());
      out.printf("등록일: %s\n", photoBoard.getCreatedDate());
      out.printf("조회수: %d\n", photoBoard.getViewCount());
      out.printf("요리: %s\n", photoBoard.getRecipe().getCook());
      out.println("사진 파일:");

      for (PhotoFile photoFile : photoBoard.getFiles()) {
        out.printf("> %s\n", photoFile.getFilepath());
      }

    } else {
      out.println("해당 번호의 사진 게시물이 없습니다.");
    }
  }
}
