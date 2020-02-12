package kny.cook.handler;

import java.util.List;
import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;

public class BoardListCommand implements Command {

  BoardDao boardDao;

  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {
    try {
      List<Board> boards = boardDao.findAll();
      for (Board b : boards) {
        System.out.printf("%d, %s, %s, %d\n", b.getNo(), b.getTitle(), b.getDate(),
            b.getViewCount());
      }
    } catch (Exception e) {
      System.out.println("명령 실행 중 오류 발생!");
    }
  }
}
