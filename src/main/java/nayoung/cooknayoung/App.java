package nayoung.cooknayoung;

import java.util.Scanner;
import nayoung.cooknayoung.handler.BoardHandler;
import nayoung.cooknayoung.handler.BoardHandler2;
import nayoung.cooknayoung.handler.MemberHandler;
import nayoung.cooknayoung.handler.RecipeHandler;

public class App {

  static Scanner keyboard = new Scanner(System.in);

  public static void main(String[] args) {
    
    RecipeHandler.keyboard = keyboard;
    MemberHandler.keyboard = keyboard;
    BoardHandler.keyboard = keyboard;
    BoardHandler2.keyboard = keyboard;

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
        case "/member/add":
          MemberHandler.addMember();
          break;
        case "/member/list":
          MemberHandler.listMember();
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
        case "/board2/add":
          BoardHandler2.addBoard();
          break;
        case "/board2/list":
          BoardHandler2.listBoard();
          break;
        case "/board2/detail":
          BoardHandler2.detailBoard();
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
  
