package nayoung.cooknayoung;

import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    
    final int SIZE = 100;
    int[] no = new int[SIZE];
    String[] name = new String[SIZE];
    String[] email = new String[SIZE];
    String[] password = new String[SIZE];
    String[] photo = new String[SIZE];
    int[] tel = new int[SIZE];

    int count = 0;
    for (int i = 0; i < SIZE; i++) {
      count++;;
      
      System.out.print("번호? ");
      no[i] = keyboard.nextInt();
      keyboard.nextLine();

      System.out.print("이름? ");
      name[i] = keyboard.nextLine();

      System.out.print("이메일? ");
      email[i] = keyboard.nextLine();

      System.out.print("암호? ");
      password[i] = keyboard.nextLine();

      System.out.print("사진? ");
      photo[i] = keyboard.nextLine();

      System.out.print("전화? ");
      tel[i] = keyboard.nextInt();
      keyboard.nextLine();

      System.out.println(); 

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break; 
      }  
    }   
    System.out.println(); 

    for (int i = 0; i <count; i++) {
      System.out.printf("%d, %s, %s, %s, %s, %d\n", 
          no[i], name[i], email[i], password[i], photo[i], tel[i]);
    }
    keyboard.close();
  }
}