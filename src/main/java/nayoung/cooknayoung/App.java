package nayoung.cooknayoung;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import nayoung.cooknayoung.domain.Board;
import nayoung.cooknayoung.domain.Member;
import nayoung.cooknayoung.domain.Recipe;
import nayoung.cooknayoung.handler.BoardAddCommand;
import nayoung.cooknayoung.handler.BoardDeleteCommand;
import nayoung.cooknayoung.handler.BoardDetailCommand;
import nayoung.cooknayoung.handler.BoardListCommand;
import nayoung.cooknayoung.handler.BoardUpdateCommand;
import nayoung.cooknayoung.handler.Command;
import nayoung.cooknayoung.handler.MemberAddCommand;
import nayoung.cooknayoung.handler.MemberDeleteCommand;
import nayoung.cooknayoung.handler.MemberDetailCommand;
import nayoung.cooknayoung.handler.MemberListCommand;
import nayoung.cooknayoung.handler.MemberUpdateCommand;
import nayoung.cooknayoung.handler.RecipeAddCommand;
import nayoung.cooknayoung.handler.RecipeDeleteCommand;
import nayoung.cooknayoung.handler.RecipeDetailCommand;
import nayoung.cooknayoung.handler.RecipeListCommand;
import nayoung.cooknayoung.handler.RecipeUpdateCommand;
import nayoung.cooknayoung.util.Prompt;


public class App {

  static Scanner keyboard = new Scanner(System.in);

  static Deque<String> commandStack = new ArrayDeque<>();
  static Queue<String> commandQueue = new LinkedList<>();


  public static void main(String[] args) {

    Prompt prompt = new Prompt(keyboard);
    HashMap<String, Command> commandMap = new HashMap<>();



    LinkedList<Recipe> recipeList = new LinkedList<>();
    commandMap.put("/recipe/add", new RecipeAddCommand(prompt, recipeList));
    commandMap.put("/recipe/delete", new RecipeDeleteCommand(prompt, recipeList));
    commandMap.put("/recipe/detail", new RecipeDetailCommand(prompt, recipeList));
    commandMap.put("/recipe/list", new RecipeListCommand(recipeList));
    commandMap.put("/recipe/update", new RecipeUpdateCommand(prompt, recipeList));

    ArrayList<Member> memberList = new ArrayList<>();
    commandMap.put("/member/add", new MemberAddCommand(prompt, memberList));
    commandMap.put("/member/delete", new MemberDeleteCommand(prompt, memberList));
    commandMap.put("/member/detail", new MemberDetailCommand(prompt, memberList));
    commandMap.put("/member/list", new MemberListCommand(memberList));
    commandMap.put("/member/update", new MemberUpdateCommand(prompt, memberList));

    LinkedList<Board> boardList = new LinkedList<>();
    commandMap.put("/board/add", new BoardAddCommand(prompt, boardList));
    commandMap.put("/board/delete", new BoardDeleteCommand(prompt, boardList));
    commandMap.put("/board/detail", new BoardDetailCommand(prompt, boardList));
    commandMap.put("/board/list", new BoardListCommand(boardList));
    commandMap.put("/board/update", new BoardUpdateCommand(prompt, boardList));


    String command;

    while (true) {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      if (command.length() == 0)
        continue;

      if (command.equals("quit")) {
        System.out.println("안녕!");
        break;
      } else if (command.equals("history")) {
        printCommandHistory(commandStack.iterator());
        continue;
      } else if (command.equals("history2")) {
        printCommandHistory(commandQueue.iterator());
        continue;
      }
      commandStack.push(command);
      commandQueue.offer(command);

      Command commandHandler = commandMap.get(command);

      if (commandHandler != null) {
        commandHandler.execute();
      } else {
        System.out.println("실행할 수 없는 명령입니다.");
      }
    }
    keyboard.close();
  }


  private static void printCommandHistory(Iterator<String> iterator) {
    int count = 0;

    while (iterator.hasNext()) {
      System.out.println(iterator.next());

      if ((++count % 5) == 0) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
}
