package nayoung.cooknayoung.handler;

import java.sql.Date;
import java.util.Scanner;
import nayoung.cooknayoung.domain.Member;
import util.ArrayList;

public class MemberHandler {
  
  ArrayList<Member> memberList;
  Scanner input;
  
  public MemberHandler(Scanner input) {
    this. input = input;
    memberList = new ArrayList<>();
  }
  
  public MemberHandler(Scanner input, int capacity) {
    this.input = input;
    memberList = new ArrayList<>(capacity);
  }
  
  public void listMember() {
    Member[] arr = memberList.toArray(new Member[this.memberList.size()]);
    
    for (Member m : arr) {
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
  
  public void detailMember() {
    System.out.println("번호? ");
    int index = input.nextInt();
    input.nextLine();
    
    Member member = (Member) this.memberList.get(index);
    
    if (member == null) {
      System.out.println("해당회원을 찾을 수 없습니다. ");
      return;
    }
    System.out.printf("번호? %d\n", member.getNo());
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword() );    
    System.out.printf("사진: %s\n", member.getPhoto());    
    System.out.printf("전화: %s\n", member.getTel());   
    System.out.printf("가입일: %s\n", member.getRegisteredDate());    
  }
  
  public void updateMember() {
    System.out.println("번호? ");
    int index = input.nextInt();
    input.nextLine();
    
    Member oldMember = this.memberList.get(index);
    
    if(oldMember ==  null) {
      System.out.println("게시글 인덱스가 유효하지 않습니다. ");
      return;
    }
    System.out.printf("이름 (%s)?",oldMember.getName());
    String name = (input.nextLine());
    System.out.printf("이메일 (%s)?",oldMember.getEmail());
    String email = (input.nextLine());
    System.out.printf("암호 (%s)?",oldMember.getPassword());
    String password = (input.nextLine());
    System.out.printf("사진(%s)?",oldMember.getPhoto());
    String photo = (input.nextLine());
    System.out.printf("전화 (%s)?",oldMember.getTel());
    String tel = (input.nextLine());
    
    if (name.length() == 0) {
      System.out.println("회원 변경을 취소했습니다.");
      return;
    }
    Member newMember = new Member(); // 새 게시물을 저장할 보드객체
    newMember.setNo(oldMember.getNo());
    newMember.setName(name);
    newMember.setEmail(email); 
    newMember.setPassword(password); 
    newMember.setPhoto(photo);
    newMember.setTel(tel);
    newMember.setRegisteredDate(oldMember.getRegisteredDate());
    
    this.memberList.set(index, newMember); // 지정된 위치에 새 게시물로 변경
    System.out.println("회원을 변경했습니다.");
  }
  
  public void deleteMember() {
    System.out.println("번호? ");
    int index = input.nextInt();
    input.nextLine();
    
    Member member = this.memberList.get(index);
    
    if(member == null) {
      System.out.println("해당 회원을 찾을 수 없습니다. ");
      return;
    }
  this.memberList.remove(index);
  System.out.println("회원을 삭제했습니다.");
  }
  
}
