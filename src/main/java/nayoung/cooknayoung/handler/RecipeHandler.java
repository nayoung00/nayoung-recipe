package nayoung.cooknayoung.handler;

import java.util.Scanner;
import nayoung.cooknayoung.domain.Recipe;

public class RecipeHandler {
  

  public static Scanner keyboard;
  static final int RECIPE_SIZE = 100;
  static Recipe[] recipes = new Recipe[RECIPE_SIZE];    
  static int recipeCount = 0;

  public static void addRecipe() {
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
  }

 public static void listRecipe() {
    for (int i = 0; i <recipeCount; i++) {
      Recipe r = recipes[i];
      System.out.printf("%d, %s, %s, %d, %d\n", 
          r.no,  r.cook,  r.method,  r.expense,  r.time);
    }       
  }
}