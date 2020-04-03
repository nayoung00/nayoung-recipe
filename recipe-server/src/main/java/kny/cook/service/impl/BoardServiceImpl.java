package kny.cook.service.impl;

import java.util.List;
import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;
import kny.cook.util.Component;

@Component
public class BoardServiceImpl implements BoardService {

  BoardDao boardDao;

  public BoardServiceImpl(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public List<Board> list() throws Exception {
    return boardDao.findAll();
  }

  @Override
  public Board get(int no) throws Exception {
    return boardDao.findByNo(no);
  }

  @Override
  public int update(Board board) throws Exception {
    return boardDao.update(board);
  }

  @Override
  public int delete(int no) throws Exception {
    return boardDao.delete(no);
  }

  @Override
  public void add(Board board) throws Exception {
    boardDao.insert(board);
  }



}
