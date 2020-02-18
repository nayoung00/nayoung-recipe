// LMS 클라이언트
package kny.cook;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import kny.cook.dao.BoardDao;
import kny.cook.dao.MemberDao;
import kny.cook.dao.RecipeDao;
import kny.cook.dao.mariadb.BoardDaoImpl;
import kny.cook.dao.mariadb.MemberDaoImpl;
import kny.cook.dao.mariadb.RecipeDaoImpl;
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

  HashMap<String, Command> commandMap = new HashMap<>();

  public ClientApp() {

    commandStack = new ArrayDeque<>();
    commandQueue = new LinkedList<>();

    BoardDao boardDao = new BoardDaoImpl();
    MemberDao memberDao = new MemberDaoImpl();
    RecipeDao recipeDao = new RecipeDaoImpl();

    commandMap.put("/recipe/list", new RecipeListCommand(recipeDao));
    commandMap.put("/recipe/add", new RecipeAddCommand(recipeDao, prompt));
    commandMap.put("/recipe/delete", new RecipeDeleteCommand(recipeDao, prompt));
    commandMap.put("/recipe/detail", new RecipeDetailCommand(recipeDao, prompt));
    commandMap.put("/recipe/update", new RecipeUpdateCommand(recipeDao, prompt));

    commandMap.put("/member/list", new MemberListCommand(memberDao));
    commandMap.put("/member/add", new MemberAddCommand(memberDao, prompt));
    commandMap.put("/member/delete", new MemberDeleteCommand(memberDao, prompt));
    commandMap.put("/member/detail", new MemberDetailCommand(memberDao, prompt));
    commandMap.put("/member/update", new MemberUpdateCommand(memberDao, prompt));

    commandMap.put("/board/list", new BoardListCommand(boardDao));
    commandMap.put("/board/add", new BoardAddCommand(boardDao, prompt));
    commandMap.put("/board/delete", new BoardDeleteCommand(boardDao, prompt));
    commandMap.put("/board/detail", new BoardDetailCommand(boardDao, prompt));
    commandMap.put("/board/update", new BoardUpdateCommand(boardDao, prompt));
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
