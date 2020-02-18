package kny.cook.handler;

import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;
import kny.cook.util.Prompt;

public class BoardAddCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;

  public BoardAddCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Board board = new Board();
    board.setTitle(prompt.inputString("내용? "));

    try {
      boardDao.insert(board);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("등록 실패!");
    }
  }
}
