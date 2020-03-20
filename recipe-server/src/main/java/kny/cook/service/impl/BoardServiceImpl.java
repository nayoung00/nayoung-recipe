package kny.cook.service.impl;

import java.util.List;
import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;

public class BoardServiceImpl implements BoardService {

  BoardDao boardDao;

  public BoardServiceImpl(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public List<Board> findAll() throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Board get(int no) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public int update(Board board) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int delete(int no) throws Exception {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public int insert(Board board) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Board findByNo(int no) {
    // TODO Auto-generated method stub
    return null;
  }

}
