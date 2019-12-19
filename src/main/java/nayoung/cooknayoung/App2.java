package nayoung.cooknayoung;

import java.util.Scanner;

public class App2 {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    
    class Member{
      int no;
      String name;
      String email;
      String password;
      String photo;
      int tel;      
    }
    
      final int SIZE = 100;
      Member[] members = new Member[SIZE];

    int count = 0;
    for (int i = 0; i < SIZE; i++) {
      count++;;
      
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
      
      members[i] = member;

      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break; 
      }  
    }   
    System.out.println(); 

    for (int i = 0; i <count; i++) {
      Member member;
      member = members[i];
      
      System.out.printf("%d, %s, %s, %s, %s, %d\n", 
          member.no, member.name, member.email, 
          member.password, member.photo, member.tel);
    }
    keyboard.close();
  }
}