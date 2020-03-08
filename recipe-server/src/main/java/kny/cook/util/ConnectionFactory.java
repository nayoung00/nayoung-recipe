package kny.cook.util;

import java.sql.Connection;
import java.sql.DriverManager;
import kny.cook.sql.ConnectionProxy;

public class ConnectionFactory {

  String jdbcUrl;
  String username;
  String password;

  ThreadLocal<Connection> connectionLocal = new ThreadLocal<>();

  public ConnectionFactory(String jdbcUrl, String username, String password) {
    this.jdbcUrl = jdbcUrl;
    this.username = username;
    this.password = password;
  }

  public Connection getConnection() throws Exception {

    Connection con = connectionLocal.get();
    if (con != null) {
      System.out.println("스레드에 보관된 Connection 객체 리턴!");
      return con;
    }
    con = new ConnectionProxy(DriverManager.getConnection(jdbcUrl, username, password));
    System.out.println("새 Connection 객체를 생성하여 리턴!");

    connectionLocal.set(con);
    return con;
  }

  public Connection removeConnection() {

    Connection con = connectionLocal.get();
    if (con != null) {
      connectionLocal.remove();
      System.out.println("스레드에 보관된 Connection 객체 제거 했음!");
    }
    return con;
  }
}
