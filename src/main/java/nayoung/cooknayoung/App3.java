package nayoung.cooknayoung;

import java.util.Scanner;

import java.sql.Date;

public class App3 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    final int SIZE = 100;

    int[] no=new int[SIZE];
    String[] title = new String[SIZE];
    Date[] date=new Date[SIZE];
    int[] viewCount = new int[SIZE];
    String response;

    int count = 0;
    for (int i = 0; i < SIZE; i++) {
      count++;

      System.out.print("번호? ");
      no[i] = keyboard.nextInt();
      keyboard.nextLine(); 

      System.out.print("내용? ");
      title[i] = keyboard.nextLine();

      date[i] = new Date(System.currentTimeMillis());

      viewCount[i] = 0;

      System.out.println();

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break; 
      }
    }

    System.out.println();

    for (int i = 0; i <count; i++) {
      System.out.printf("%d, %s, %s, %d\n", no[i], title[i], date[i], viewCount[i]);
    }
    keyboard.close();
  }
}