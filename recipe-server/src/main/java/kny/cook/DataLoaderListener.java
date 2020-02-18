package kny.cook;

import java.util.Map;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.json.BoardJsonFileDao;
import kny.cook.dao.json.MemberJsonFileDao;
import kny.cook.dao.json.RecipeJsonFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");

    context.put("boardDao", new BoardJsonFileDao("./board.json"));
    context.put("recipeDao", new RecipeJsonFileDao("./recipe.json"));
    context.put("memberDao", new MemberJsonFileDao("./member.json"));

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {}


}
