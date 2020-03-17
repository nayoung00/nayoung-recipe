package kny.cook;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.session.SqlSessionFactory;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.BoardDao;
import kny.cook.dao.MemberDao;
import kny.cook.dao.PhotoBoardDao;
import kny.cook.dao.PhotoFileDao;
import kny.cook.dao.RecipeDao;
import kny.cook.servlet.BoardAddServlet;
import kny.cook.servlet.BoardDeleteServlet;
import kny.cook.servlet.BoardDetailServlet;
import kny.cook.servlet.BoardListServlet;
import kny.cook.servlet.BoardUpdateServlet;
import kny.cook.servlet.LoginServlet;
import kny.cook.servlet.MemberAddServlet;
import kny.cook.servlet.MemberDeleteServlet;
import kny.cook.servlet.MemberDetailServlet;
import kny.cook.servlet.MemberListServlet;
import kny.cook.servlet.MemberSearchServlet;
import kny.cook.servlet.MemberUpdateServlet;
import kny.cook.servlet.PhotoBoardAddServlet;
import kny.cook.servlet.PhotoBoardDeleteServlet;
import kny.cook.servlet.PhotoBoardDetailServlet;
import kny.cook.servlet.PhotoBoardListServlet;
import kny.cook.servlet.PhotoBoardUpdateServlet;
import kny.cook.servlet.RecipeAddServlet;
import kny.cook.servlet.RecipeDeleteServlet;
import kny.cook.servlet.RecipeDetailServlet;
import kny.cook.servlet.RecipeListServlet;
import kny.cook.servlet.RecipeUpdateServlet;
import kny.cook.servlet.Servlet;
import kny.cook.sql.PlatformTransactionManager;
import kny.cook.sql.SqlSessionFactoryProxy;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();

  Map<String, Object> context = new HashMap<>();

  Map<String, Servlet> servletMap = new HashMap<>();

  ExecutorService executorService = Executors.newCachedThreadPool();

  boolean shotdown = false;

  public void addApplicationContextListener(ApplicationContextListener listener) {
    listeners.add(listener);
  }

  public void removeApplicationContextListener(ApplicationContextListener listener) {
    listeners.remove(listener);
  }

  private void notifyApplicationInitialized() {
    for (ApplicationContextListener listener : listeners)
      listener.contextInitialized(context);
  }

  private void notifyApplicationDestroyed() {
    for (ApplicationContextListener listener : listeners)
      listener.contextDestroyed(context);
  }

  public void service() {

    notifyApplicationInitialized();

    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) context.get("SqlSessionFactory");

    BoardDao boardDao = (BoardDao) context.get("boardDao");
    RecipeDao recipeDao = (RecipeDao) context.get("recipeDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");
    PhotoBoardDao photoBoardDao = (PhotoBoardDao) context.get("photoBoardDao");
    PhotoFileDao photoFileDao = (PhotoFileDao) context.get("photoFileDao");

    PlatformTransactionManager txManager =
        (PlatformTransactionManager) context.get("transactionManager");

    servletMap.put("/recipe/list", new RecipeListServlet(recipeDao));
    servletMap.put("/recipe/add", new RecipeAddServlet(recipeDao));
    servletMap.put("/recipe/detail", new RecipeDetailServlet(recipeDao));
    servletMap.put("/recipe/delete", new RecipeDeleteServlet(recipeDao));
    servletMap.put("/recipe/update", new RecipeUpdateServlet(recipeDao));

    servletMap.put("/member/list", new MemberListServlet(memberDao));
    servletMap.put("/member/add", new MemberAddServlet(memberDao));
    servletMap.put("/member/detail", new MemberDetailServlet(memberDao));
    servletMap.put("/member/delete", new MemberDeleteServlet(memberDao));
    servletMap.put("/member/update", new MemberUpdateServlet(memberDao));
    servletMap.put("/member/search", new MemberSearchServlet(memberDao));

    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));

    servletMap.put("/photoboard/list", new PhotoBoardListServlet(photoBoardDao, recipeDao));
    servletMap.put("/photoboard/add",
        new PhotoBoardAddServlet(txManager, photoBoardDao, recipeDao, photoFileDao));
    servletMap.put("/photoboard/detail", new PhotoBoardDetailServlet(photoBoardDao, photoFileDao));
    servletMap.put("/photoboard/delete",
        new PhotoBoardDeleteServlet(txManager, photoBoardDao, photoFileDao));
    servletMap.put("/photoboard/update",
        new PhotoBoardUpdateServlet(txManager, photoBoardDao, photoFileDao));

    servletMap.put("/auth/login", new LoginServlet(memberDao));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트와 연결 대기 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결 되었음!");

        executorService.submit(() -> {
          processRequest(socket);

          ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();

          System.out.println("---------------------------------");
        });

        if (shotdown) {
          break;
        }
      }
    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    executorService.shutdown();


    while (true) {
      if (executorService.isTerminated()) {
        break;
      }
      try {
        Thread.sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    notifyApplicationDestroyed();

    System.out.println("서버 종료!");
  }

  void processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      String request = in.nextLine();
      System.out.printf("=> %s\n", request);

      if (request.equalsIgnoreCase("/shotdown")) {
        quit(out);
        return;
      }

      Servlet servlet = servletMap.get(request);

      if (servlet != null) {
        try {
          servlet.service(in, out);

        } catch (Exception e) {
          out.println("요청 처리 중 오류 발생! ");
          out.println(e.getMessage());

          System.out.println("클라이언트 요청 처리 중 오류 발생:");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }
      out.println("!end!");
      out.flush();
      System.out.println("클라이언트에게 응답하였음!");

    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
    }
  }

  private void notFound(PrintStream out) throws IOException {
    out.println("요청한 명령을 처리할 수 없습니다.");
  }

  private void quit(PrintStream out) throws IOException {
    shotdown = true;
    out.println("OK");
    out.println("!end!");
    out.flush();
  }

  public static void main(String[] args) {
    System.out.println("서버 레시피 관리 시스템입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}

