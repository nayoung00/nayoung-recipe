package kny.cook.service;

import java.util.List;
import kny.cook.domain.Board;

public interface BoardService {

  List<Board> findAll() throws Exception;

  Board get(int no) throws Exception;

  int update(Board board) throws Exception;

  int delete(int no) throws Exception;

  int insert(Board board);

  Board findByNo(int no);


}
