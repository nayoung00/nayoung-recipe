// LMS 클라이언트
package kny.cook;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner; 
import kny.cook.dao.proxy.BoardDaoProxy;
import kny.cook.dao.proxy.DaoProxyHelper;
import kny.cook.dao.proxy.MemberDaoProxy;
import kny.cook.dao.proxy.RecipeDaoProxy;
import kny.cook.handler.BoardAddCommand;
import kny.cook.handler.BoardDeleteCommand;
import kny.cook.handler.BoardDetailCommand;
import kny.cook.handler.BoardListCommand;
import kny.cook.handler.BoardUpdateCommand;
import kny.cook.handler.Command;
import kny.cook.handler.MemberAddCommand;
import kny.cook.handler.MemberDeleteCommand;
import kny.cook.handler.MemberDetailCommand;
import kny.cook.handler.MemberListCommand;
import kny.cook.handler.MemberUpdateCommand;
import kny.cook.handler.RecipeAddCommand;
import kny.cook.handler.RecipeDeleteCommand;
import kny.cook.handler.RecipeDetailCommand;
import kny.cook.handler.RecipeListCommand;
import kny.cook.handler.RecipeUpdateCommand;
import kny.cook.util.Prompt;

public class ClientApp {

  Scanner keyboard = new Scanner(System.in);
  Prompt prompt = new Prompt(keyboard);

  Deque<String> commandStack;
  Queue<String> commandQueue;

  String host;
  int port;

  HashMap<String, Command> commandMap = new HashMap<>();

  public ClientApp() {

    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();

    try {
      host = prompt.inputString("서버?");
      port = prompt.inputInt("포트?");

    } catch (Exception e) {
      System.out.println("서버 주소 또는 포트 번호가 유효하지 않습니다.");
      keyboard.close();
      return;
    }
    DaoProxyHelper daoProxyHelper = new DaoProxyHelper(host, port);

    BoardDaoProxy BoardDao = new BoardDaoProxy(daoProxyHelper);
    RecipeDaoProxy RecipeDao = new RecipeDaoProxy(daoProxyHelper);
    MemberDaoProxy MemberDao = new MemberDaoProxy(daoProxyHelper);

    commandMap.put("/recipe/list", new RecipeListCommand(RecipeDao));
    commandMap.put("/recipe/add", new RecipeAddCommand(RecipeDao, prompt));
    commandMap.put("/recipe/delete", new RecipeDeleteCommand(RecipeDao, prompt));
    commandMap.put("/recipe/detail", new RecipeDetailCommand(RecipeDao, prompt));
    commandMap.put("/recipe/update", new RecipeUpdateCommand(RecipeDao, prompt));

    commandMap.put("/member/list", new MemberListCommand(MemberDao));
    commandMap.put("/member/add", new MemberAddCommand(MemberDao, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(MemberDao, prompt));
    commandMap.put("/member/detail", new MemberDetailCommand(MemberDao, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(MemberDao, prompt));

    commandMap.put("/board/list", new BoardListCommand(BoardDao));
    commandMap.put("/board/add", new BoardAddCommand(BoardDao, prompt));
    commandMap.put("/board/delete", new BoardDeleteCommand(BoardDao, prompt));
    commandMap.put("/board/detail", new BoardDetailCommand(BoardDao, prompt));
    commandMap.put("/board/update", new BoardUpdateCommand(BoardDao, prompt));

    commandMap.put("/server/stop", () -> {
      try {
        try (Socket socket = new Socket(host, port);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

          out.writeUTF("/server/stop");
          out.flush();
          System.out.println("서버: " + in.readUTF());
          System.out.println("안녕!");
        }
      } catch (Exception e) {
      }
    });
  }

  public void service() {


    while (true) {
      String command;
      command = prompt.inputString("\n명령> ");

      if (command.length() == 0)
        continue;
      if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      } else if (command.equals("quit")) {
        break;
      }
      commandStack.push(command);
      commandQueue.offer(command);

      processCommand(command);

    }
  }

  private void processCommand(String command) {
    Command commandHandler = commandMap.get(command);
    if (commandHandler == null) {
      System.out.println("실행할 수 없는 명령입니다.");
      return;
    }
    commandHandler.execute();
  }

  private void printCommandHistory(Iterator<String> iterator) {
    int count = 0;
    while (iterator.hasNext()) {
      System.out.println(iterator.next());

      if ((++count % 5) == 0) {
        String str = prompt.inputString(":");
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }

  public static void main(String[] args) throws Exception {
    System.out.println("클라이언트 레시피 관리 시스템입니다.");

    ClientApp app = new ClientApp();
    app.service();
  }
}
