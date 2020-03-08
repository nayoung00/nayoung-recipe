package kny.cook.sql;

import java.sql.Connection;

public class PlatformTransactionManager {

  DataSource dataSource;

  public PlatformTransactionManager(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public void beginTransaction() throws Exception {
    Connection con = dataSource.getConnection();
    con.setAutoCommit(false);
  }

  public void commit() throws Exception {
    Connection con = dataSource.getConnection();
    con.commit();
    con.setAutoCommit(true);
  }

  public void rollback() throws Exception {
    Connection con = dataSource.getConnection();
    con.rollback();
    con.setAutoCommit(true);
  }
}
