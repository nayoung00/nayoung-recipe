package nayoung.cooknayoung.handler;

import java.sql.Date;
import nayoung.cooknayoung.domain.Board;
import util.ArrayList;
import util.Prompt;


public class BoardHandler {

  ArrayList<Board> BoardList;
  
  Prompt prompt;

  public BoardHandler(Prompt prompt) {
    this.prompt = prompt;
    this.BoardList = new ArrayList<>();
  }

  public BoardHandler(Prompt prompt, int capacity) {
    this.prompt = prompt;
    this.BoardList = new ArrayList<>(capacity);
  }

  public void listBoard() {

    Board[] arr = new Board[this.BoardList.size()];
    this.BoardList.toArray(arr);

    for (Board b : arr) {
      System.out.printf("%d, %s, %s, %d\n", 
          b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

  public void addBoard() {
    Board board = new Board();

    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);

    this.BoardList.add(board);

    System.out.println("저장하였습니다.");
  }

  public void detailBoard() {
    int index = indexOfBoardList(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("게시글 인덱스가 유효하지 않습니다. ");
      return;
    }
    
    Board board = this.BoardList.get(index);
    System.out.printf("번호: %d\n", board.getNo());
    System.out.printf("제목: %s\n", board.getTitle());
    System.out.printf("등록일: %s\n", board.getDate());
    System.out.printf("조회수: %d\n", board.getViewCount());    
  }

  public void updateBoard() {
    int index = indexOfBoardList(prompt.inputInt("번호? "));

    if(index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다. ");
      return;
    }

    Board oldBoard = this.BoardList.get(index);
    Board newBoard = new Board();

    newBoard.setNo(oldBoard.getNo());
    newBoard.setViewCount(oldBoard.getViewCount());
    newBoard.setDate(new Date(System.currentTimeMillis()));
    newBoard.setTitle(prompt.inputString(
        String.format("내용(%s)?" , oldBoard.getTitle()),
        oldBoard.getTitle()));
    
    if (newBoard.equals(oldBoard)) {
      System.out.println("게시글 변경을 취소했습니다.");
      return;
    }

    this.BoardList.set(index, newBoard);
    System.out.println("게시글을 변경했습니다.");
  }

  public void deleteBoard() {
    
    int index = indexOfBoardList(prompt.inputInt("번호? "));


    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다. ");
      return;
    }
    
    this.BoardList.remove(index);
    
    System.out.println("게시글을 삭제했습니다.");
  }
  

  private int indexOfBoardList(int no) {
    for(int i = 0; i < this.BoardList.size(); i++){
      if(this.BoardList.get(i).getNo() == no)
      return i;
    }
    return -1;
    
  }
}

