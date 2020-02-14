package kny.cook.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Worker {

  Object execute(ObjectInputStream in, ObjectOutputStream out) throws Exception;

}
