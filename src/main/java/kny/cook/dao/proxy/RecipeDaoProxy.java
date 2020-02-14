package kny.cook.dao.proxy;

import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeDaoProxy implements RecipeDao {

  DaoProxyHelper daoProxyHelper;
  private Worker worker;

  public RecipeDaoProxy(DaoProxyHelper daoProxyHelper) {

    this.daoProxyHelper = daoProxyHelper;
  }

  @Override
  public int insert(Recipe recipe) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/recipe/add");
      out.writeObject(recipe);
      out.flush();
      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Recipe> findAll() throws Exception {
    return (List<Recipe>) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/recipe/list");
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (List<Recipe>) in.readObject();
    });
  }


  @Override
  public Recipe findByNo(int no) throws Exception {
    return (Recipe) daoProxyHelper.request((in, out) -> {
      out.writeUTF("/recipe/detail");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return (Recipe) in.readObject();
    });
  }

  @Override
  public int update(Recipe recipe) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/recipe/update");
      out.writeObject(recipe);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }

  @Override
  public int delete(int no) throws Exception {
    return (int) daoProxyHelper.request((in, out) -> {

      out.writeUTF("/recipe/delete");
      out.writeInt(no);
      out.flush();

      String response = in.readUTF();
      if (response.equals("FAIL")) {
        throw new Exception(in.readUTF());
      }
      return 1;
    });
  }
}
