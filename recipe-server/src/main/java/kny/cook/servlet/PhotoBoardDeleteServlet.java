package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.PhotoBoardDao;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    out.println("번호? \n!{}!");
    out.flush();

    int no = Integer.parseInt(in.nextLine());

    if (photoBoardDao.delete(no) > 0) {
      out.println("사진 게시글을 삭제했습니다.");
    } else {
      out.println("해당 번호의 사진 게시물이 없습니다.");
    }
  }
}
