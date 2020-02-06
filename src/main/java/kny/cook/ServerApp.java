// LMS 서버
package kny.cook;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import kny.cook.context.ApplicationContextListener;
import kny.cook.domain.Board;
import kny.cook.domain.Member;
import kny.cook.domain.Recipe;

public class ServerApp {

  Set<ApplicationContextListener> listeners = new HashSet<>();
  Map<String, Object> context = new HashMap<>();

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

  @SuppressWarnings("unchecked")
  public void service() {

    notifyApplicationInitialized();

    List<Board> boardList = (List<Board>) context.get("boardList");
    List<Recipe> recipeList = (List<Recipe>) context.get("recipeList");
    List<Member> memberList = (List<Member>) context.get("memberList");

    notifyApplicationDestroyed();
  }

  public static void main(String[] args) {
    System.out.println("서버 레시피 관리 시스템입니다.");

    ServerApp app = new ServerApp();
    app.addApplicationContextListener(new DataLoaderListener());
    app.service();
  }

  static void processRequest(Socket clientSocket) {
    try (Socket socket = clientSocket;

        Scanner in = new Scanner(socket.getInputStream());
        PrintStream out = new PrintStream(socket.getOutputStream())) {

      System.out.println("통신을 위한 입출력 스트림을 준비하였음!");

      String message = in.nextLine();
      System.out.println("클라이언트가 보낸 메시지를 수신하였음!");
      System.out.println("클라이언트: " + message);

      out.println("Hi(나영)");
      System.out.println("클라이언트로 메시지를 전송하였음.");
    } catch (IOException e) {
      System.out.println("예외 발생: ");
      e.printStackTrace();
    }
  }
}
