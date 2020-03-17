package kny.cook.sql;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class PlatformTransactionManager {

  SqlSessionFactory sqlSessionFactory;

  public PlatformTransactionManager(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }


  public void beginTransaction() throws Exception {

    ((SqlSessionFactoryProxy) sqlSessionFactory).closeSession();

    sqlSessionFactory.openSession(false);

  }

  public void commit() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.commit();
  }

  public void rollback() throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    sqlSession.rollback();
  }
}
