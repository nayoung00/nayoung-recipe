package kny.cook.handler;

import java.sql.Date;
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
    board.setNo(prompt.inputInt("번호? "));
    board.setTitle(prompt.inputString("내용? "));
    board.setDate(new Date(System.currentTimeMillis()));
    board.setViewCount(0);


    try {
      boardDao.insert(board);
      System.out.println("저장하였습니다.");

    } catch (Exception e) {
      System.out.println("등록 실패!");
    }
  }
}
