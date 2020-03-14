package kny.cook.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import kny.cook.dao.RecipeDao;
import kny.cook.domain.Recipe;

public class RecipeDaoImpl implements RecipeDao {

  SqlSessionFactory sqlSessionFactory;

  public RecipeDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public int insert(Recipe recipe) throws Exception {

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.insert("RecipeMapper.insertRecipe", recipe);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public List<Recipe> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("RecipeMApper.selectRecipe");
    }
  }

  @Override
  public Recipe findByNo(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("RecipeMapper.detailRecipe", no);
    }
  }

  @Override
  public int update(Recipe recipe) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.update("RecipeMapper.updateRecipe", recipe);
      sqlSession.commit();
      return count;
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      int count = sqlSession.delete("RecipeMapper.deleteRecipe", no);
      sqlSession.commit();
      return count;
    }
  }
}
