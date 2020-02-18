package kny.cook.handler;

import kny.cook.dao.BoardDao;
import kny.cook.util.Prompt;

public class BoardDeleteCommand implements Command {
  BoardDao boardDao;
  Prompt prompt;


  public BoardDeleteCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");


      if (boardDao.delete(no) > 0) {
        System.out.println("게시글을 삭제했습니다.");
      } else {
        System.out.println("해당 번호의 게시글이 없습니다.");
      }
    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
