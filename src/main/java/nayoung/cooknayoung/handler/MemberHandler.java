package nayoung.cooknayoung.handler;

import java.sql.Date;
import java.util.Scanner;
import nayoung.cooknayoung.domain.Member;

public class MemberHandler {
  
  ArrayList memberList;
  
  Scanner input;

  
  public MemberHandler(Scanner input) {
    this. input = input;
    memberList = new ArrayList();
  }
  
  public MemberHandler(Scanner input, int capacity) {
    this.input = input;
    memberList = new ArrayList(capacity);
  }
  
  public void listMember() {
    Object[] arr = memberList.toArray();
    for (Object obj : arr) {
      Member m = (Member) obj;
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

    memberList.add(member);
    
    System.out.println("저장하였습니다.");
  }
  
}
