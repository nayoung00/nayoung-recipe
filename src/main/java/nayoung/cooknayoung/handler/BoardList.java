package nayoung.cooknayoung.handler;

import java.util.Arrays;
import nayoung.cooknayoung.domain.Board;

public class BoardList {

  static final int DEFAULT_CAPACITY = 100;

  Board[] list;
  int size = 0;

  public BoardList() {
    this. list = new Board[DEFAULT_CAPACITY];
  }

  public BoardList(int capacity) {
    if (capacity < DEFAULT_CAPACITY || capacity > 10000)
      this.list = new Board[DEFAULT_CAPACITY];
    else
      this.list = new Board[capacity];
  }

  public Board[] toArray() {

    return Arrays.copyOf(this.list, this.size);
  }
    
  public void add(Board board) {
    if(this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
 
      this.list = Arrays.copyOf(this.list, newCapacity);
      System.out.printf("새 배열을 %d개 생성하였음! ", newCapacity);
    }
    this.list[this.size++] = board;
  }
  public Board get(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.list[i].getNo() == no) {
        return this.list[i];
      }
    }
    return null;
  }
}