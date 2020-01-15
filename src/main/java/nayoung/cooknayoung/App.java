package nayoung.cooknayoung;

import java.util.Scanner;
import nayoung.cooknayoung.domain.Board;
import nayoung.cooknayoung.domain.Member;
import nayoung.cooknayoung.domain.Recipe;
import nayoung.cooknayoung.handler.BoardHandler;
import nayoung.cooknayoung.handler.MemberHandler;
import nayoung.cooknayoung.handler.RecipeHandler;
import nayoung.cooknayoung.util.ArrayList;
import nayoung.cooknayoung.util.Iterator;
import nayoung.cooknayoung.util.LinkedList;
import nayoung.cooknayoung.util.Prompt;
import nayoung.cooknayoung.util.Queue;
import nayoung.cooknayoung.util.Stack;


public class App {

  static Scanner keyboard = new Scanner(System.in);

  static Stack<String> commandStack = new Stack<>();
  static Queue<String> commandQueue = new Queue<>();


  public static void main(String[] args) {

    Prompt prompt = new Prompt(keyboard); 

    LinkedList<Recipe> recipeList = new LinkedList<>();
    RecipeHandler recipeHandler = new RecipeHandler(prompt, recipeList);

    ArrayList<Member> memberList = new ArrayList<>();
    MemberHandler memberHandler = new MemberHandler(prompt, memberList);

    LinkedList<Board> boardList = new LinkedList<>();
    BoardHandler boardHandler = new BoardHandler(prompt, boardList);

    String command;

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();
      
      if(command.length() == 0)
        continue;

      commandStack.push(command);      
      commandQueue.offer(command);

      switch (command) {
        case "/recipe/add":
          recipeHandler.addRecipe();
          break;
        case "/recipe/list":
          recipeHandler.listRecipe();
          break;
        case "/recipe/detail":
          recipeHandler.detailRecipe();
          break;
        case "/recipe/update":
          recipeHandler.updateRecipe();
          break;
        case "/recipe/delete":
          recipeHandler.deleteRecipe();
          break;
        case "/member/add":
          memberHandler.addMember();
          break;
        case "/member/list":
          memberHandler.listMember();
          break;
        case "/member/detail":
          memberHandler.detailMember();
          break;
        case "/member/update":
          memberHandler.updateMember();
          break;
        case "/member/delete":
          memberHandler.deleteMember();
          break;
        case "/board/add":
          boardHandler.addBoard();
          break;
        case "/board/list":
          boardHandler.listBoard();
          break;
        case "/board/detail":
          boardHandler.detailBoard();
          break;
        case "/board/update":
          boardHandler.updateBoard();
          break;
        case "/board/delete":
          boardHandler.deleteBoard();
          break;
        case "history":
          printCommandHistory(commandStack.iterator());
          break;
        case "history2":
          printCommandHistory(commandQueue.iterator());
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
