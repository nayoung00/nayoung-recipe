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
    BoardJsonFileDao boardDao = new BoardJsonFileDao("./board.json");
    RecipeJsonFileDao recipeDao = new RecipeJsonFileDao("./recipe.json");
    MemberJsonFileDao memberDao = new MemberJsonFileDao("./member.json");


    context.put("boardDao", boardDao);
    context.put("memberDao", memberDao);
    context.put("recipeDao", recipeDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    // TODO Auto-generated method stub

  }


}
