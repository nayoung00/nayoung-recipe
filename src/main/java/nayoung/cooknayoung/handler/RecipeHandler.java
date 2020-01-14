package nayoung.cooknayoung.handler;

import nayoung.cooknayoung.domain.Recipe;
import util.ArrayList;
import util.Prompt;

public class RecipeHandler {

  ArrayList<Recipe> RecipeList;

  Prompt prompt;

  public RecipeHandler(Prompt prompt) {
    this. prompt = prompt;
    RecipeList = new ArrayList<>();
  }
  public RecipeHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    RecipeList = new ArrayList<>();
  }

  public void addRecipe() {
    Recipe recipe = new Recipe();

    recipe.setNo(prompt.inputInt("번호? "));
    recipe.setCook(prompt.inputString("요리? "));
    recipe.setMaterial(prompt.inputString("재료? "));
    recipe.setMethod(prompt.inputString("방법? "));
    recipe.setExpense(prompt.inputInt("비용? "));
    recipe.setTime(prompt.inputInt("시간? "));

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
    int index = indexOfRecipe(prompt.inputInt("번호?"));


    if (index == -1) {
      System.out.println("해당 번호의 레시피가 없습니다.");
      return;
    }
    Recipe recipe = this.RecipeList.get(index);
    
    System.out.printf("번호? %d\n", recipe.getNo());
    System.out.printf("요리: %s\n", recipe.getCook());
    System.out.printf("재료: %s\n", recipe.getMaterial());
    System.out.printf("방법 : %s\n", recipe.getMethod());
    System.out.printf("비용: %d\n", recipe.getExpense());
    System.out.printf("시간: %d\n", recipe.getTime());
  }

  public void updateRecipe() {
    int index = indexOfRecipe(prompt.inputInt("번호?? "));


    if (index == -1) {
      System.out.println("해당 번호의 레시피가 없습니다. ");
      return;
    }

    Recipe oldRecipe = this.RecipeList.get(index);
    Recipe newRecipe = new Recipe();

    newRecipe.setNo(oldRecipe.getNo());

    newRecipe.setCook(prompt.inputString(
        String.format("요리(%s)?", oldRecipe.getCook(), 
            oldRecipe.getCook())));

    newRecipe.setMaterial(prompt.inputString(
        String.format("재료(%s)?", oldRecipe.getMaterial(), 
            oldRecipe.getMaterial())));


    newRecipe.setMethod(prompt.inputString(
        String.format("방법(%s)?", oldRecipe.getMethod(), 
            oldRecipe.getMethod())));

    newRecipe.setExpense(prompt.inputInt(
        String.format("비용(%d)?", oldRecipe.getExpense()),
        oldRecipe.getExpense()));

    newRecipe.setTime(prompt.inputInt(
        String.format("시간(%d)?", oldRecipe.getTime()),
        oldRecipe.getTime()));


    if (oldRecipe.equals(newRecipe)) {
      System.out.println("레시피 변경을 취소하였습니다.");
      return;
    }
    this.RecipeList.set(index, newRecipe);
    System.out.println("요리를 변경했습니다.");
  }

  public void deleteRecipe() {
    int index = indexOfRecipe(prompt.inputInt("번호? "));

    if(index == -1l) {
      System.out.println("해당 번호의 레시피가 없습니다. ");
      return;
    }

    this.RecipeList.remove(index);
    System.out.println("레시피를 삭제했습니다.");
  }

  private int indexOfRecipe(int no) {
    for(int i=0; i< this.RecipeList.size(); i++) {
      if(this.RecipeList.get(i).getNo() == no)
        return i;
    }
    return -1;
  }

}

