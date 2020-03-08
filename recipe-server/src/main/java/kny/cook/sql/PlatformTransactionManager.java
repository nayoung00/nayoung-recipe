package kny.cook.sql;

import java.sql.Connection;
import kny.cook.util.ConnectionFactory;

public class PlatformTransactionManager {

  ConnectionFactory conFactory;

  public PlatformTransactionManager(ConnectionFactory conFactory) {
    this.conFactory = conFactory;
  }

  public void beginTransaction() throws Exception {
    Connection con = conFactory.getConnection();
    con.setAutoCommit(false);
  }

  public void commit() throws Exception {
    Connection con = conFactory.getConnection();
    con.commit();
    con.setAutoCommit(true);
  }

  public void rollback() throws Exception {
    Connection con = conFactory.getConnection();
    con.rollback();
    con.setAutoCommit(true);
  }
}
