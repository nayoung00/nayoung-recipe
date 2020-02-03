package kny.cook.domain;

import java.io.Serializable;

public class Recipe implements Serializable {

  private static final long serialVersionUID = 20200203L;


  private int no;
  private String cook;
  private String material;
  private String method;
  private int expense;
  private int time;

  public static Recipe valueOf(String csv) {
    String[] data = csv.split(",");

    Recipe recipe = new Recipe();
    recipe.setNo(Integer.parseInt(data[0]));
    recipe.setCook(data[1]);
    recipe.setMaterial(data[2]);
    recipe.setMethod(data[3]);
    recipe.setExpense(Integer.parseInt(data[4]));
    recipe.setTime(Integer.parseInt(data[5]));

    return recipe;
  }

  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%d,%d", this.getNo(), this.getCook(), this.getMaterial(),
        this.getMethod(), this.getExpense(), this.getTime());
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getCook() {
    return cook;
  }

  public void setCook(String cook) {
    this.cook = cook;
  }

  public String getMaterial() {
    return material;
  }

  public void setMaterial(String material) {
    this.material = material;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public int getExpense() {
    return expense;
  }

  public void setExpense(int expense) {
    this.expense = expense;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }
}
