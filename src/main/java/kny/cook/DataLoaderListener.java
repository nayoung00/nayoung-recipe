package kny.cook;

import java.util.Map;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.BoardObjectFileDao;
import kny.cook.dao.MemberObjectFileDao;
import kny.cook.dao.RecipeObjectFileDao;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("데이터를 로딩합니다.");
    BoardObjectFileDao boardDao = new BoardObjectFileDao("./board.ser2");
    RecipeObjectFileDao recipeDao = new RecipeObjectFileDao("./recipe.ser2");
    MemberObjectFileDao memberDao = new MemberObjectFileDao("./member.ser2");


    context.put("boardDao", boardDao);
    context.put("memberDao", memberDao);
    context.put("recipeDao", recipeDao);
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    // TODO Auto-generated method stub

  }


}
