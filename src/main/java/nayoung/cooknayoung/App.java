package nayoung.cooknayoung;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
  
    class Recipe{
      int no;
      String cook;
      String material;
      String method;
      int expense;
      int time;     
    }
    
    final int SIZE = 100;
    Recipe[] recipes = new Recipe[SIZE];

    int count = 0;
    for (int i = 0; i < 100; i++) {
      count++;
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

      recipes[i] = recipe; 
      
      System.out.print("계속 입력하시겠습니까?(Y/n) ");
      String response = keyboard.nextLine();
      if (!response.equalsIgnoreCase("y")) {
        break; 
      }  
      System.out.println(); 
    }   

    System.out.println();

    for (int i = 0; i <count; i++) {
      
      Recipe recipe;
      recipe = recipes[i];
      
      System.out.printf("%d, %s, %s, %d, %d\n", 
          recipe.no,  recipe.cook,  recipe.material,  recipe.expense,  recipe.time);
    }
    
    keyboard.close();
  }
}