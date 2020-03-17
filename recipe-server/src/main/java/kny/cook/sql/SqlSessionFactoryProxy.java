package kny.cook.sql;

import java.sql.Connection;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;

public class SqlSessionFactoryProxy implements SqlSessionFactory {

  SqlSessionFactory originalFactory;

  ThreadLocal<SqlSession> sqlSessionLocal = new ThreadLocal<>();

  public SqlSessionFactoryProxy(SqlSessionFactory originalFactory) {
    this.originalFactory = originalFactory;
  }

  public void closeSession() {
    SqlSession sqlSession = sqlSessionLocal.get();

    if (sqlSession != null) {
      sqlSessionLocal.remove();

      ((SqlSessionProxy) sqlSession).realClose();
    }
  }

  @Override
  public SqlSession openSession() {
    return this.openSession(true);
  }

  @Override
  public SqlSession openSession(boolean autoCommit) {

    SqlSession sqlSession = sqlSessionLocal.get();

    if (sqlSession == null) {

      sqlSession = new SqlSessionProxy(originalFactory.openSession(autoCommit));

      sqlSessionLocal.set(sqlSession);
    }
    return sqlSession;
  }

  @Override
  public SqlSession openSession(Connection connection) {
    return originalFactory.openSession(connection);
  }

  @Override
  public SqlSession openSession(TransactionIsolationLevel level) {
    return originalFactory.openSession(level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType) {
    return originalFactory.openSession(execType);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
    return originalFactory.openSession(execType, autoCommit);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level) {
    return originalFactory.openSession(execType, level);
  }

  @Override
  public SqlSession openSession(ExecutorType execType, Connection connection) {
    return originalFactory.openSession(execType, connection);
  }

  @Override
  public Configuration getConfiguration() {
    return originalFactory.getConfiguration();
  }

}
