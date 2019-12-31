package nayoung.cooknayoung.domain;

public class Recipe {
  
  private int no;
  private String cook;
  private String material;
  private String method;
  private int expense;
  private int time;
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
