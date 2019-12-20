package nayoung.cooknayoung;

import java.sql.Date;
import java.util.Scanner;

public class App {

  static String command;

  static class Recipe{
    int no;
    String cook;
    String material;
    String method;
    int expense;
    int time;     
  }
  static final int RECIPE_SIZE = 100;
  static Recipe[] recipes = new Recipe[RECIPE_SIZE];    
  static int recipeCount = 0;


  static class Member{
    int no;
    String name;
    String email;
    String password;
    String photo;
    int tel;      
  }
  static final int MEMBER_SIZE = 100;
  static Member[] members = new Member[MEMBER_SIZE];
  static int memberCount = 0;


  static class Board{
    int no;
    String title;
    Date date;
    int viewCount;
  }
  static final int BOARD_SIZE = 100;
  static Board[] boards = new Board[BOARD_SIZE];
  static int boardCount = 0;


  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);


    do {
      System.out.print("\n명령> ");
      command = keyboard.nextLine();

      switch (command) {
        case "/recipe/add":
          Recipe recipe = new Recipe();

          System.out.print("번호? ");
          recipe.no = keyboard.nextInt();
          keyboard.nextLine();

          System.out.print("요리? ");
          recipe.cook = keyboard.nextLine();

          System.out.print("재료? ");
          recipe.material = keyboard.nextLine();

          System.out.print("방법? ");
          recipe.method = keyboard.nextLine();

          System.out.print("비용? ");
          recipe.expense = keyboard.nextInt();
          keyboard.nextLine();

          System.out.print("시간? ");
          recipe.time = keyboard.nextInt();
          keyboard.nextLine();

          System.out.println(); 

          recipes[recipeCount++] = recipe; 
          System.out.println("저장하였습니다.");
          break;


        case "/recipe/list":
          for (int i = 0; i <recipeCount; i++) {
            Recipe r = recipes[i];
            System.out.printf("%d, %s, %s, %d, %d\n", 
                r.no,  r.cook,  r.method,  r.expense,  r.time);
          }       
          break;

        case "/member/add":             
          Member member = new Member();

          System.out.print("번호? ");
          member.no = keyboard.nextInt();
          keyboard.nextLine();

          System.out.print("이름? ");
          member.name = keyboard.nextLine();

          System.out.print("이메일? ");
          member.email = keyboard.nextLine();

          System.out.print("암호? ");
          member.password = keyboard.nextLine();

          System.out.print("사진? ");
          member.photo = keyboard.nextLine();

          System.out.print("전화? ");
          member.tel = keyboard.nextInt();
          keyboard.nextLine();

          System.out.println(); 

          members[memberCount++] = member;
          System.out.println("저장하였습니다.");    
          break;

        case "/member/list":
          for (int i = 0; i < memberCount; i++) {
            Member m = members[i];
            System.out.printf("%d, %s, %s, %s, %s, %d\n", 
                m.no, m.name, m.email, 
                m.password, m.photo, m.tel);
          }
          break;


        case "/board/add":
          Board board = new Board();

          System.out.print("번호? ");
          board.no = keyboard.nextInt();
          keyboard.nextLine(); 

          System.out.print("내용? ");
          board.title = keyboard.nextLine();

          board.date = new Date(System.currentTimeMillis());

          board.viewCount = 0;

          System.out.println();

          boards[boardCount++] = board;
          System.out.println("저장하였습니다.");    
          break;

        case "/board/list":
          for (int i = 0; i <boardCount; i++) {
            Board b = boards[i];
            System.out.printf("%d, %s, %s, %d\n", 
                b.no,  b.title,  b.date,  b.viewCount);
          }
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

