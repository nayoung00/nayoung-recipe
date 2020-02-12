package kny.cook.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.dao.BoardDao;
import kny.cook.domain.Board;

public class BoardDaoProxy implements BoardDao {

  ObjectInputStream in;
  ObjectOutputStream out;

  public BoardDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(Board board) throws Exception {
    out.writeUTF("/baord/add");
    out.writeObject(board);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Board> findAll() throws Exception {
    out.writeUTF("/board/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<Board>) in.readObject();
  }

  @Override
  public Board findByNo(int no) throws Exception {
    out.writeUTF("/board/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (Board) in.readObject();
  }

  @Override
  public int update(Board board) throws Exception {
    out.writeUTF("/board/update");
    out.writeObject(board);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/baord/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }
}
