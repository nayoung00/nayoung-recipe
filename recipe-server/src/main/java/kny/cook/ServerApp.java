// LMS 서버
package kny.cook;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.BoardDao;
import kny.cook.dao.MemberDao;
import kny.cook.dao.RecipeDao;
import kny.cook.servlet.BoardAddServlet;
import kny.cook.servlet.BoardDeleteServlet;
import kny.cook.servlet.BoardDetailServlet;
import kny.cook.servlet.BoardListServlet;
import kny.cook.servlet.BoardUpdateServlet;
import kny.cook.servlet.MemberAddServlet;
import kny.cook.servlet.MemberDeleteServlet;
import kny.cook.servlet.MemberDetailServlet;
import kny.cook.servlet.MemberListServlet;
import kny.cook.servlet.MemberUpdateServlet;
import kny.cook.servlet.RecipeAddServlet;
import kny.cook.servlet.RecipeDeleteServlet;
import kny.cook.servlet.RecipeDetailServlet;
import kny.cook.servlet.RecipeListServlet;
import kny.cook.servlet.RecipeUpdateServlet;
import kny.cook.servlet.Servlet;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

  Map<String, Servlet> servletMap = new HashMap<>();

  ExecutorService executorService = Executors.newCachedThreadPool();

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

    class RequestProcessor implements Runnable {
      Socket socekt;

      public RequestProcessor(Socket socket) {
        this.socekt = socket;
      }

      @Override
      public void run() {
        processRequest(socekt);
        System.out.println("---------------------------------");
      }

    }

    notifyApplicationInitialized();

    BoardDao boardDao = (BoardDao) context.get("boardDao");
    RecipeDao recipeDao = (RecipeDao) context.get("recipeDao");
    MemberDao memberDao = (MemberDao) context.get("memberDao");

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

    servletMap.put("/board/list", new BoardListServlet(boardDao));
    servletMap.put("/board/add", new BoardAddServlet(boardDao));
    servletMap.put("/board/detail", new BoardDetailServlet(boardDao));
    servletMap.put("/board/delete", new BoardDeleteServlet(boardDao));
    servletMap.put("/board/update", new BoardUpdateServlet(boardDao));

    try (ServerSocket serverSocket = new ServerSocket(9999)) {
      System.out.println("클라이언트와 연결 대기 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        System.out.println("클라이언트와 연결 되었음!");


        executorService.submit(() -> {
          processRequest(socket);
          System.out.println("---------------------------------");
        });
      }

    } catch (Exception e) {
      System.out.println("서버 준비 중 오류 발생!");
    }

    notifyApplicationDestroyed();

    executorService.shutdown();

  }

  int processRequest(Socket clientSocket) {

    try (Socket socket = clientSocket;
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      String request = in.readUTF();
      System.out.println("클라이언트가 보낸 메시지를 수신하였음!");

      if (request.equalsIgnoreCase("/server/stop")) {
        quit(out);
        return 9;
      }

      Servlet servlet = servletMap.get(request);

      if (servlet != null) {
        try {
          servlet.service(in, out);
        } catch (Exception e) {
          out.writeUTF("FAIL");
          out.writeUTF(e.getMessage());

          System.out.println("클라이언트 요청 처리 중 오류 발생:");
          e.printStackTrace();
        }
      } else {
        notFound(out);
      }
      out.flush();
      System.out.println("클라이언트에게 응답하였음!");

      return 0;
    } catch (Exception e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
      return -1;
    }
  }

  private void notFound(ObjectOutputStream out) throws IOException {
    out.writeUTF("FAIL");
    out.writeUTF("요청한 명령을 처리할 수 없습니다.");
  }

  private void quit(ObjectOutputStream out) throws IOException {
    out.writeUTF("OK");
    out.flush();
  }

  public static void main(String[] args) {
    System.out.println("서버 레시피 관리 시스템입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }
}

