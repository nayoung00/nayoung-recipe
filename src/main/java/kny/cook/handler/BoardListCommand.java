package kny.cook.handler;

import java.util.AbstractList;
import java.util.Iterator;
import kny.cook.domain.Board;

public class BoardListCommand implements Command {

  AbstractList<Board> boardList;


  public BoardListCommand(AbstractList<Board> list) {
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
