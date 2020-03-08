package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.PhotoFileDao;
import kny.cook.sql.PlatformTransactionManager;
import kny.cook.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  PlatformTransactionManager txManager;
  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PlatformTransactionManager txManager, PhotoBoardDao photoBoardDao,
      PhotoFileDao photoFileDao) {
    this.txManager = txManager;
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    txManager.beginTransaction();

    try {
      photoFileDao.deleteAll(no);

      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }
      txManager.commit();
      out.println("사진 게시글을 삭제했습니다.");

    } catch (Exception e) {
      txManager.rollback();
      out.println(e.getMessage());
    }
  }
}
