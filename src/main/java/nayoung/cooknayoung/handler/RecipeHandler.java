package nayoung.cooknayoung.handler;

import java.util.Scanner;
import nayoung.cooknayoung.domain.Recipe;
import util.ArrayList;

public class RecipeHandler {
  
  ArrayList<Recipe> RecipeList;
  Scanner input;
  
  public RecipeHandler(Scanner input) {
    this. input = input;
    RecipeList = new ArrayList<>();
  }
  public RecipeHandler(Scanner input, int capacity) {
    this.input = input;
    RecipeList = new ArrayList<>();
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

    RecipeList.add(recipe);
    
    System.out.println("저장하였습니다.");
  }

  public void listRecipe() {
    Recipe[] arr = RecipeList.toArray(new Recipe[this.RecipeList.size()]);
    for (Recipe r : arr) {
      System.out.printf("%d, %s, %s, %d, %d\n", 
          r.getNo(),  r.getCook(),  r.getMethod(),  r.getExpense(),  r.getTime());
    }       
  }
  
  public void detailRecipe() {
    System.out.println("레시피 인덱스? ");
    int index = input.nextInt();
    input.nextLine();
    
    Recipe recipe = this.RecipeList.get(index);
    
    if (recipe == null) {
      System.out.println(" 레시피 인덱스가 유효하지 않습니다.");
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
    System.out.println("레시피 인덱스? ");
    int index = input.nextInt();
    input.nextLine(); 

    Recipe oldRecipe = this.RecipeList.get(index); 

    if (oldRecipe == null) {
      System.out.println("해당 요리를 찾을 수 없습니다. ");
      return;
    }
    
    boolean changed = false;
    String inputStr = null;
    Recipe newRecipe = new Recipe();
    
    newRecipe.setNo(oldRecipe.getNo());
    
    System.out.printf("요리(%s)?", oldRecipe.getCook());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecipe.setCook(oldRecipe.getCook());
    } else {
      newRecipe.setCook(inputStr);
      changed = true;
    }
    System.out.printf("재료(%s)?", oldRecipe.getMaterial());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecipe.setMaterial(oldRecipe.getMaterial());
    } else {
      newRecipe.setMaterial(inputStr);
      changed = true;
    }
    System.out.printf("방법(%s)?", oldRecipe.getMethod());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecipe.setMethod(oldRecipe.getMethod());
    } else {
      newRecipe.setMethod(inputStr);
      changed = true;
    }
    
    System.out.printf("비용(%d)?", oldRecipe.getExpense());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecipe.setExpense(oldRecipe.getExpense());
    } else {
      newRecipe.setExpense(Integer.parseInt(inputStr));
      changed = true;
    }
    System.out.printf("시간(%d)?", oldRecipe.getTime());
    inputStr = input.nextLine();
    if (inputStr.length() == 0) {
      newRecipe.setTime(oldRecipe.getTime());
    } else {
      newRecipe.setTime(Integer.parseInt(inputStr));
      changed = true;
    }

    this.RecipeList.set(index, newRecipe);
    System.out.println("요리를 변경했습니다.");
  }
  
  public void deleteRecipe() {
    System.out.println("번호? ");
    int index = input.nextInt();
    input.nextLine();
    
    Recipe recipe = this.RecipeList.get(index);
    
    if(recipe == null) {
      System.out.println("해당 요리를 찾을 수 없습니다. ");
      return;
    }
    this.RecipeList.remove(index);
    System.out.println("요리를 삭제했습니다.");
  }
  
  private int indexOfRecipeList(int no) {
    for(int i=0; i< this.RecipeList.size(); i++) {
      if(this.RecipeList.get(i).getNo() == no)
        return i;
    }
    return -1;
    }
      
  }

