package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.DataLoaderListener;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.PhotoFileDao;
import kny.cook.util.Prompt;

public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardDao photoBoardDao;
  PhotoFileDao photoFileDao;

  public PhotoBoardDeleteServlet(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

    DataLoaderListener.con.setAutoCommit(false);

    try {
      photoFileDao.deleteAll(no);

      if (photoBoardDao.delete(no) == 0) {
        throw new Exception("해당 번호의 사진 게시글이 없습니다.");
      }
      DataLoaderListener.con.commit();
      out.println("사진 게시글을 삭제했습니다.");

    } catch (Exception e) {
      DataLoaderListener.con.rollback();
      out.println(e.getMessage());

    } finally {
      DataLoaderListener.con.setAutoCommit(true);
    }
  }
}
