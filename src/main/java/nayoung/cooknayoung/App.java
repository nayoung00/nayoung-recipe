package nayoung.cooknayoung;

import java.util.Scanner;
import nayoung.cooknayoung.handler.BoardHandler;
import nayoung.cooknayoung.handler.MemberHandler;
import nayoung.cooknayoung.handler.RecipeHandler;
import util.Prompt;
import util.Queue;
import util.Stack;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  static Stack<String> commandStack = new Stack<>();
  static Queue<String> commandQueue = new Queue<>();


  public static void main(String[] args) {

    Prompt prompt = new Prompt(keyboard); 

    RecipeHandler RecipeHandler = new RecipeHandler(prompt);
    MemberHandler MemberHandler = new MemberHandler(prompt);
    BoardHandler BoardHandler = new BoardHandler(prompt);

    String command;
    
    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      
      commandStack.push(command);      
      commandQueue.offer(command);

      switch (command) {
        case "/recipe/add":
          RecipeHandler.addRecipe();
          break;
        case "/recipe/list":
          RecipeHandler.listRecipe();
          break;
        case "/recipe/detail":
          RecipeHandler.detailRecipe();
          break;
        case "/recipe/update":
          RecipeHandler.updateRecipe();
          break;
        case "/recipe/delete":
          RecipeHandler.deleteRecipe();
          break;
        case "/member/add":
          MemberHandler.addMember();
          break;
        case "/member/list":
          MemberHandler.listMember();
          break;
        case "/member/detail":
          MemberHandler.detailMember();
          break;
        case "/member/update":
          MemberHandler.updateMember();
          break;
        case "/member/delete":
          MemberHandler.deleteMember();
          break;
        case "/board/add":
          BoardHandler.addBoard();
          break;
        case "/board/list":
          BoardHandler.listBoard();
          break;
        case "/board/detail":
          BoardHandler.detailBoard();
          break;
        case "/board/update":
          BoardHandler.updateBoard();
          break;
        case "/board/delete":
          BoardHandler.deleteBoard();
          break;
        case "history":
          printCommandHistory();
          break;
        case "history2":
          printCommandHistory2();
          break;
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));

    System.out.println("안녕!");

    keyboard.close();
  }
  private static void printCommandHistory() {
    Stack<String> historyStack = commandStack.clone();
    int count = 0;
    while (!historyStack.empty()) {
      System.out.println(historyStack.pop());

      if ((++count % 5 ) == 0 ) {
        System.out.print(":");
        String str = keyboard.nextLine();
        if (str.equalsIgnoreCase("q")) {
          break;
        }
      }
    }
  }
  
  private static void printCommandHistory2() {
    Queue<String> historyQueue = commandQueue.clone();
    int count = 0;
    
    while (historyQueue.size() > 0) {
      System.out.println(historyQueue.poll());
      
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
