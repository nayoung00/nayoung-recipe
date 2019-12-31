package nayoung.cooknayoung.handler;

import java.util.Scanner;
import nayoung.cooknayoung.domain.Member;
import nayoung.cooknayoung.domain.Recipe;

public class RecipeHandler {
  
  
  Recipe[] recipes;    
  int recipeCount = 0;
  Scanner input;
  
  static final int RECIPE_SIZE = 100;
  
  public RecipeHandler(Scanner input) {
    this. input = input;
    this. recipes = new Recipe[RECIPE_SIZE];
  }
  
  public RecipeHandler(Scanner input, int capacity) {
    this.input = input;
    if (capacity < RECIPE_SIZE || capacity > 10000)
      this.recipes = new Recipe[RECIPE_SIZE];
    else
      this.recipes = new Recipe[capacity];
  }


  public void addRecipe() {
    Recipe recipe = new Recipe();

    System.out.print("번호? ");
    recipe.setNo(input.nextInt());
    input.nextLine();

    System.out.print("요리? ");
    recipe.setCook(input.nextLine());

    System.out.print("재료? ");
    recipe.setMaterial(input.nextLine());

    System.out.print("방법? ");
    recipe.setMethod(input.nextLine());

    System.out.print("비용? ");
    recipe.setExpense(input.nextInt());
    input.nextLine();

    System.out.print("시간? ");
    recipe.setTime(input.nextInt());
    input.nextLine();

    System.out.println(); 

    this.recipes[this.recipeCount++] = recipe; 
    System.out.println("저장하였습니다.");
  }

 public void listRecipe() {
    for (int i = 0; i <this.recipeCount; i++) {
      Recipe r = this.recipes[i];
      System.out.printf("%d, %s, %s, %d, %d\n", 
          r.getNo(),  r.getCook(),  r.getMethod(),  r.getExpense(),  r.getTime());
    }       
  }
}