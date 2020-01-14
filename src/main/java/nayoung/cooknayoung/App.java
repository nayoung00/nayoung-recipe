package nayoung.cooknayoung;

import java.util.Scanner;
import nayoung.cooknayoung.handler.BoardHandler;
import nayoung.cooknayoung.handler.MemberHandler;
import nayoung.cooknayoung.handler.RecipeHandler;
import util.Prompt;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {
    
    Prompt prompt = new Prompt(keyboard); 

    RecipeHandler RecipeHandler = new RecipeHandler(prompt);
    MemberHandler MemberHandler = new MemberHandler(prompt);
    BoardHandler BoardHandler = new BoardHandler(prompt);

    String command;

    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

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
        default:
          if (!command.equalsIgnoreCase("quit")) {
            System.out.println("실행할 수 없는 명령입니다.");
          }
      }
    } while (!command.equalsIgnoreCase("quit"));

    System.out.println("안녕!");

    keyboard.close();
  }
}

