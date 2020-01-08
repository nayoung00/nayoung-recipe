package nayoung.cooknayoung.handler;

import java.util.Scanner;
import nayoung.cooknayoung.domain.Recipe;
import util.ArrayList;

public class RecipeHandler {
  
  ArrayList<Recipe> recipeList;
  Scanner input;
  
  public RecipeHandler(Scanner input) {
    this. input = input;
    recipeList = new ArrayList<>();
  }
  public RecipeHandler(Scanner input, int capacity) {
    this.input = input;
    recipeList = new ArrayList<>();
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
    Recipe[] arr = recipeList.toArray(new Recipe[this.recipeList.size()]);
    for (Recipe r : arr) {
      System.out.printf("%d, %s, %s, %d, %d\n", 
          r.getNo(),  r.getCook(),  r.getMethod(),  r.getExpense(),  r.getTime());
    }       
  }
  
  public void detailRecipe() {
    System.out.println("번호? ");
    int index = input.nextInt();
    input.nextLine();
    
    Recipe recipe = this.recipeList.get(index);
    
    if (recipe == null) {
      System.out.println(" 해당 수업을 찾을 수 없습니다.");
      return;
    }
    System.out.printf("번호? %d\n", recipe.getNo());
    System.out.printf("요리: %s\n", recipe.getCook());
    System.out.printf("재료: %s\n", recipe.getMaterial());
    System.out.printf("방법 : %s\n", recipe.getMethod());
    System.out.printf("비용: %d\n", recipe.getExpense());
    System.out.printf("시간: %d\n", recipe.getTime());
  }
  
  public void updateRecipe() {
    System.out.println("번호? ");
    int index = input.nextInt();
    input.nextLine(); 

    Recipe oldRecipe = this.recipeList.get(index); 

    if (oldRecipe == null) {
      System.out.println("해당 요리를 찾을 수 없습니다. ");
      return;
    }
    
    System.out.printf("요리방법 (%s)?",oldRecipe.getMethod());
    String Method = (input.nextLine());
    
    if (Method.length() == 0 ) {
      System.out.println("요리 변경을 취소했습니다.");
      return;
    }

    Recipe newRecipe = new Recipe();
    newRecipe.setNo(oldRecipe.getNo());
    newRecipe.setCook(oldRecipe.getCook());
    newRecipe.setMaterial(oldRecipe.getMaterial());
    newRecipe.setMethod(Method);
    newRecipe.setExpense(oldRecipe.getExpense());    
    newRecipe.setTime(oldRecipe.getTime());
    
    this.recipeList.set(index, newRecipe);
    System.out.println("요리를 변경했습니다.");
  }
  
  public void deleteRecipe() {
    System.out.println("번호? ");
    int index = input.nextInt();
    input.nextLine();
    
    Recipe recipe = this.recipeList.get(index);
    
    if(recipe == null) {
      System.out.println("해당 요리를 찾을 수 없습니다. ");
      return;
    }
    this.recipeList.remove(index);
    System.out.println("요리를 삭제했습니다.");
  }
}

