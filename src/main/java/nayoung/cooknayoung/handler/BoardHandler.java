package nayoung.cooknayoung.handler;

import java.sql.Date;
import java.util.Scanner;
import nayoung.cooknayoung.domain.Board;
import util.ArrayList;

public class BoardHandler {

  ArrayList<Board> boardList;
  Scanner input;

  public BoardHandler(Scanner input) {
    this. input = input;
    boardList = new ArrayList<>();
  }

  public BoardHandler(Scanner input, int capacity) {
    this.input = input;
    boardList = new ArrayList<>(capacity);
  }

  public void listBoard() {

    Board[] arr = boardList.toArray(new Board[this.boardList.size()]);

    for (Board b : arr) {
      System.out.printf("%d, %s, %s, %d\n", 
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void addBoard() {
    Board board = new Board();

    System.out.print("번호? ");
    board.setNo(input.nextInt());
    input.nextLine(); // 줄바꿈 기호 제거용

    System.out.print("내용? ");
    board.setTitle(input.nextLine());

    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    boardList.add(board);

    System.out.println("저장하였습니다.");
  }

  public void detailBoard() {
    System.out.println("게시물 인덱스? ");
    int index = input.nextInt();
    input.nextLine(); 

    Board board = (Board) this.boardList.get(index);

    if (board == null) {
      System.out.println("게시물 인덱스가 유효하지 않습니다. ");
      return;
    }

    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", board.getDate());
    System.out.printf("조회수: %d\n", board.getViewCount());    
  }

  public void updateBoard() {
    System.out.println("게시물 인덱스? ");
    int index = input.nextInt();
    input.nextLine(); 

    Board oldBoard = (Board) this.boardList.get(index);

    if (oldBoard == null) {
      System.out.println("게시물 인덱스가 유효하지 않습니다. ");
      return;
    }  
    System.out.printf("내용(%s)?", oldBoard.getTitle());
    String title = (input.nextLine());

    if (title.length() == 0 ) {
      System.out.println("게시글 변경을 취소했습니다.");
      return;
    }
    Board newBoard = new Board();
    newBoard.setNo(oldBoard.getNo());
    newBoard.setViewCount(oldBoard.getViewCount());
    newBoard.setTitle(title); // 새로운 내용으로 저장
    newBoard.setDate(new Date(System.currentTimeMillis()));

    this.boardList.set(index, newBoard);
    System.out.println("게시글을 변경했습니다.");
  }
  
  public void deleteBoard() {
    System.out.println("게시글 인덱스? ");
    int index = input.nextInt();
    input.nextLine();

    Board board = this.boardList.get(index); 

    if (board == null) {
      System.out.println("게시글 인덱스가 유효하지 않습니다.");
      return;
    }
    this.boardList.remove(index);
    System.out.println("게시글을 삭제했습니다.");
  }
}


