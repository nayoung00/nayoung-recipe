package nayoung.cooknayoung;

import java.util.Scanner;

import java.sql.Date;

public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    class Board{
    int no;
    String title;
    Date date;
    int viewCount;
    }
    
    final int SIZE = 100;
    Board[] boards = new Board[SIZE];
    
    String response;
    
    int count = 0;
    for (int i = 0; i < SIZE; i++) {
      count++;
      
      Board board = new Board();
      
      System.out.print("번호? ");
      board.no = keyboard.nextInt();
      keyboard.nextLine(); 

      System.out.print("내용? ");
      board.title = keyboard.nextLine();

      board.date = new Date(System.currentTimeMillis());

      board.viewCount = 0;

      System.out.println();
      
      boards[i] = board;

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break;    
      }
    }

    System.out.println();

    for (int i = 0; i <count; i++) {
      Board board;
      board = boards[i];
      
      System.out.printf("%d, %s, %s, %d\n", 
          board.no,  board.title,  board.date,  board.viewCount);
    }
    keyboard.close();
  }
}