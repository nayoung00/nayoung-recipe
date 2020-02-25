package kny.cook.dao.json;

import java.util.List;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeJsonFileDao extends AbstractJsonFileDao<Recipe> implements RecipeDao {

  public RecipeJsonFileDao(String filename) {
    super(filename);
  }

  @Override
  public int insert(Recipe recipe) throws Exception {
    if (indexOf(recipe.getNo()) > -1) {
      return 0;
    }

    list.add(recipe);
    saveData();
    return 1;
  }

  @Override
  public List<Recipe> findAll() throws Exception {
    return list;
  }

  @Override
  public Recipe findByNo(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return null;
    }
    return list.get(index);
  }

  @Override
  public int update(Recipe recipe) throws Exception {
    int index = indexOf(recipe.getNo());
    if (index == -1) {
      return 0;
    }
    list.set(index, recipe);
    saveData();
    return 1;
  }

  @Override
  public int delete(int no) throws Exception {
    int index = indexOf(no);
    if (index == -1) {
      return 0;

    }
    list.remove(index);
    saveData();
    return 1;

  }

  @Override
  protected <k> int indexOf(k key) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == (int) key)
        return i;
    }
    return -1;
  }
}