package kny.cook.handler;

import java.util.AbstractList;
import kny.cook.domain.Board;
import kny.cook.util.Prompt;

public class BoardDeleteCommand implements Command {

  AbstractList<Board> boardList;

  Prompt prompt;

  public BoardDeleteCommand(Prompt prompt, AbstractList<Board> list) {
    this.prompt = prompt;
    this.boardList = list;
  }

  @Override
  public void execute() {
    int index = indexOfBoard(prompt.inputInt("번호? "));

    if (index == -1) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    this.boardList.remove(index);

    System.out.println("게시글을 삭제했습니다.");
  }

  private int indexOfBoard(int no) {
    for (int i = 0; i < this.boardList.size(); i++) {
      if (this.boardList.get(i).getNo() == no) {
        return i;
      }
    }
    return -1;
  }

}
