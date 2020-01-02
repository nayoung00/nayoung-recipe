package nayoung.cooknayoung.handler;

import java.util.Scanner;
import nayoung.cooknayoung.domain.Recipe;

public class RecipeHandler {
  
  ArrayList recipeList;

  Scanner input;
  
  public RecipeHandler(Scanner input) {
    this. input = input;
    recipeList = new ArrayList();
  }
  
  public RecipeHandler(Scanner input, int capacity) {
    this.input = input;
    recipeList = new ArrayList();
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

    recipeList.add(recipe);
    
    System.out.println("저장하였습니다.");
  }

  public void listRecipe() {
    Object[] arr = recipeList.toArray();
    for (Object obj : arr) {
      Recipe r = (Recipe)obj;
      System.out.printf("%d, %s, %s, %d, %d\n", 
          r.getNo(),  r.getCook(),  r.getMethod(),  r.getExpense(),  r.getTime());
    }       
  }
}