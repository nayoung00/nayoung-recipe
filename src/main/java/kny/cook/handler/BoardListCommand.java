package kny.cook.handler;

import java.util.Iterator;
import java.util.List;
import kny.cook.domain.Board;

public class BoardListCommand implements Command {

  List<Board> boardList;


  public BoardListCommand(List<Board> list) {
    this.boardList = list;
  }

  @Override
  public void execute() {

    Iterator<Board> iterator = boardList.iterator();
    while (iterator.hasNext()) {
      Board b = iterator.next();
      System.out.printf("%d, %s, %s, %d\n", b.getNo(), b.getTitle(), b.getDate(), b.getViewCount());
    }
  }

}
