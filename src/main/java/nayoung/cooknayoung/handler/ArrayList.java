package nayoung.cooknayoung.handler;

import java.util.Arrays;

public class ArrayList {

  static final int DEFAULT_CAPACITY = 100;

  Object[] list;
  int size = 0;

  public ArrayList() {
    this. list = new Object[DEFAULT_CAPACITY];
  }

  public ArrayList(int capacity) {
    if (capacity < DEFAULT_CAPACITY || capacity > 10000)
      this.list = new Object[DEFAULT_CAPACITY];
    else
      this.list = new Object[capacity];
  }

  public Object[] toArray() {
    return Arrays.copyOf(this.list, this.size);
  }

  public void add(Object obj) {
    if(this.size == this.list.length) {

      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);

      Object[] arr = new Object[newCapacity];
      for(int i = 0; i < this.list.length; i++) {
        arr[i] = this.list[i];
      }
      this.list = arr;

      this.list = Arrays.copyOf(this.list, newCapacity);
      System.out.printf("새 배열을 %d개 생성하였음! ", newCapacity);
    }
    this.list[this.size++] = obj;
  }
  public Object get(int idx) {
    if (idx >= 0 && idx < this.size) {
      return this.list[idx];
    } else {
      return null;
    }
  }
 
}
