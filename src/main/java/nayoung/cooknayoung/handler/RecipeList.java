package nayoung.cooknayoung.handler;

import java.util.Arrays;
import nayoung.cooknayoung.domain.Recipe;

public class RecipeList {

  static final int RECIPE_SIZE = 100;

  Recipe[] list;    
  int size = 0;

  public RecipeList() {
    this. list = new Recipe[RECIPE_SIZE];
  }

  public RecipeList(int capacity) {  
    if (capacity < RECIPE_SIZE || capacity > 10000)
      this.list = new Recipe[RECIPE_SIZE];
    else
      this.list = new Recipe[capacity];
  }

  public Recipe[] toArray() {

    return Arrays.copyOf(this.list, this.size);
  }

  public void add(Recipe recipe) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
      this.list = Arrays.copyOf(this.list, newCapacity);
    }
    this.list[this.size++] = recipe;
  }
  public Recipe get(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.list[i].getNo() == no) {
        return this.list[i];
      }
    }
    return null;
  }
}
