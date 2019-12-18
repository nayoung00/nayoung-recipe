package nayoung.cooknayoung;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    final int SIZE = 100;

    int[] no = new int[SIZE];
    String[] cook = new String[SIZE];
    String[] material = new String[SIZE];
    String[] method = new String[SIZE];
    int[] expense = new int[SIZE];
    int[] time = new int[SIZE];


    int count = 0;
    for (int i = 0; i < 100; i++) {
      count++;

      System.out.print("번호? ");
      no[i] = keyboard.nextInt();
      keyboard.nextLine();

      System.out.print("요리? ");
      cook[i] = keyboard.nextLine();

      System.out.print("재료? ");
      material[i] = keyboard.nextLine();

      System.out.print("방법? ");
      method[i] = keyboard.nextLine();

      System.out.print("비용? ");
      expense[i] = keyboard.nextInt();
      keyboard.nextLine();

      System.out.print("시간? ");
      time[i] = keyboard.nextInt();
      keyboard.nextLine();

      System.out.println(); 

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break; 
      }  
      System.out.println(); 
    }   

    System.out.println();

    for (int i = 0; i <count; i++) {
      System.out.printf("%d, %s, %s, %d, %d\n", 
          no[i], cook[i], material[i], expense[i], time[i]);
    }
    keyboard.close();
  }
}