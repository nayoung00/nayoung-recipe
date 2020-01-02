package nayoung.cooknayoung.handler;

import java.util.Arrays;
import nayoung.cooknayoung.domain.Member;

public class MemberList {

  static final int MEMBER_SIZE = 100;

  Member[] list;
  int size = 0;

  public MemberList() {
    this.list = new Member[MEMBER_SIZE];
  }

  public MemberList(int capacity) {
    if (capacity < MEMBER_SIZE || capacity > 10000)
      this.list = new Member[MEMBER_SIZE];
    else
      this.list = new Member[capacity];
  }

  public Member[] toArray() {

    return Arrays.copyOf(this.list, this.size);
  }

  public void add(Member member) {
    if (this.size == this.list.length) {
      int oldCapacity = this.list.length;
      int newCapacity = oldCapacity + (oldCapacity >> 1);
   
      this.list = Arrays.copyOf(this.list, newCapacity);
      System.out.printf("새 배열을 %d개 생성하였음! ", newCapacity);
    } 
    this.list[this.size++] = member;  
  }
  
  public Member get(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.list[i].getNo() == no) {
        return this.list[i];
      }
    }
    return null;
  }
}