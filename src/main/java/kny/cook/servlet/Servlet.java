package kny.cook.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Servlet {
  void service(ObjectOutputStream out, ObjectInputStream in) throws Exception;

}
