package nayoung.cooknayoung.handler;

import java.sql.Date;
import java.util.Scanner;
import nayoung.cooknayoung.domain.Board;
import nayoung.cooknayoung.domain.Member;

public class MemberHandler {

  Member[] members;
  int memberCount = 0;
  
  public static Scanner input;
  static final int MEMBER_SIZE = 100;
  
  public MemberHandler(Scanner input) {
    this. input = input;
    this. members = new Member[MEMBER_SIZE];
  }
  
  public MemberHandler(Scanner input, int capacity) {
    this.input = input;
    if (capacity < MEMBER_SIZE || capacity > 10000)
      this.members = new Member[MEMBER_SIZE];
    else
      this.members = new Member[capacity];
  }
  
  
  public void listMember() {
    for (int i = 0; i < this.memberCount; i++) {
      Member m = this.members[i];
      System.out.printf("%d, %s, %s, %s, %s\n", 
          m.getNo(), m.getName(), m.getEmail(), m.getTel(), m.getRegisteredDate());
    }
  }

  public void addMember() {
    Member member = new Member();

    System.out.print("번호? ");
    member.setNo(input.nextInt());
    input.nextLine(); // 줄바꿈 기호 제거용

    System.out.print("이름? ");
    member.setName(input.nextLine());

    System.out.print("이메일? ");
    member.setEmail(input.nextLine());

    System.out.print("암호? ");
    member.setPassword(input.nextLine());

    System.out.print("사진? ");
    member.setPhoto(input.nextLine());

    System.out.print("전화? ");
    member.setTel(input.nextLine());

    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    this.members[this.memberCount++] = member;
    System.out.println("저장하였습니다.");
  }
  
}
