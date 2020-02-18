
package kny.cook.handler;

import java.util.List;
import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;

public class BoardListCommand implements Command {

  BoardDao boardDao;

  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    try {
      List<Board> boards = boardDao.findAll();

      for (Board board : boards) {
        System.out.printf("%d, %s, %s, %d\n", board.getNo(), board.getTitle(), board.getDate(),
            board.getViewCount());
      }
    } catch (Exception e) {
      System.out.println("목록 조회 실패!");
    }
  }
}
