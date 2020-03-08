package kny.cook.util;

import java.sql.Connection;
import java.sql.DriverManager;

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
    if (con!= null) {
     System.out.println("스레드에 보관된 Connection 객체 리턴!");
     return con;
    }
    
    con = DriverManager.getConnection(jdbcUrl, username, password);
    System.out.println("새 Connection 객체를 생성하여 리턴!");
    
    connectionLocal.set(con);
    return con;
  }

  public void removeConnection() {
    try {
    Connection con = connectionLocal.get();
    System.out.println("reoveConnection() : " + con);
    if (con != null) {
      connectionLocal.remove();
    }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}
