package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.domain.PhotoBoard;

public class PhotoBoardUpdateServlet implements Servlet {

  PhotoBoardDao photoBoardDao;

  public PhotoBoardUpdateServlet(PhotoBoardDao photoBoardDao) {
    this.photoBoardDao = photoBoardDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {
    out.println("번호? \n!{}!");
    out.flush();
    int no = Integer.parseInt(in.nextLine());

    PhotoBoard old = photoBoardDao.findByNo(no);
    if (old == null) {
      out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    out.printf("제목(%s)? \n!{}!\n", old.getTitle());
    PhotoBoard photoBoard = new PhotoBoard();
    photoBoard.setTitle(in.nextLine());
    photoBoard.setNo(no);

    if (photoBoardDao.update(photoBoard) > 0) {
      out.println("게시글을 변경했습니다.");
    } else {
      out.println("게시글 변경에 실패했습니다.");
    }
  }
}
