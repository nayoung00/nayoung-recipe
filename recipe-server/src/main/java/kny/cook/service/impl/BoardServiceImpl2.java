package kny.cook.service.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;
import kny.cook.service.BoardService;

public class BoardServiceImpl2 implements BoardService {

  SqlSessionFactory sqlSessionFactory;

  public BoardServiceImpl2(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void add(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);

      boardDao.insert(board);
    }
  }

  @Override
  public List<Board> list() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.findAll();
    }
  }

  @Override
  public Board get(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.findByNo(no);
    }
  }

  @Override
  public int update(Board board) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.update(board);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
      return boardDao.delete(no);
    }
  }



}
