package kny.cook.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeDaoProxy implements RecipeDao {

  ObjectInputStream in;
  ObjectOutputStream out;

  public RecipeDaoProxy(ObjectInputStream in, ObjectOutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public int insert(Recipe recipe) throws Exception {
    out.writeUTF("/recipe/add");
    out.writeObject(recipe);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Recipe> findAll() throws Exception {
    out.writeUTF("/recipe/list");
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (List<Recipe>) in.readObject();
  }

  @Override
  public Recipe findByNo(int no) throws Exception {
    out.writeUTF("/recipe/detail");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return (Recipe) in.readObject();
  }

  @Override
  public int update(Recipe recipe) throws Exception {
    out.writeUTF("/recipe/update");
    out.writeObject(recipe);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    out.writeUTF("/recipe/delete");
    out.writeInt(no);
    out.flush();

    String response = in.readUTF();
    if (response.equals("FAIL")) {
      throw new Exception(in.readUTF());
    }
    return 1;
  }
}
