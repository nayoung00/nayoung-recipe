package kny.cook.handler;

import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;
import kny.cook.util.Prompt;

public class BoardUpdateCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;


  public BoardUpdateCommand(BoardDao boardDao, Prompt prompt) {

    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Board oldBoard = null;

      try {
        oldBoard = boardDao.findByNo(no);
      } catch (Exception e) {
        System.out.println("해당 번호의 게시물이 없습니다!");
        return;
      }

      Board newBoard = new Board();
      newBoard.setNo(oldBoard.getNo());


      newBoard.setTitle(
          prompt.inputString(String.format("내용(%s)? ", oldBoard.getTitle()), oldBoard.getTitle()));

      boardDao.update(newBoard);
      System.out.println("게시글을 변경했습니다.");
    } catch (Exception e) {
      System.out.println("변경 실패!");
    }
  }
}
