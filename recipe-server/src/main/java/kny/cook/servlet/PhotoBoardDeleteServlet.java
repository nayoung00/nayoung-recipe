package kny.cook.servlet;

import java.io.PrintStream;
import java.util.Scanner;
import kny.cook.service.PhotoBoardService;
import kny.cook.util.Component;
import kny.cook.util.Prompt;

@Component("/photoboard/delete")
public class PhotoBoardDeleteServlet implements Servlet {

  PhotoBoardService photoBoardService;

  public PhotoBoardDeleteServlet(PhotoBoardService photoBoardService) {
    this.photoBoardService = photoBoardService;
  }

  @Override
  public void service(Scanner in, PrintStream out) throws Exception {

    int no = Prompt.getInt(in, out, "번호? ");

  }
}
