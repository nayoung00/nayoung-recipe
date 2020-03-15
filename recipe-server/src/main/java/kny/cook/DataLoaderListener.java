package kny.cook;

import java.io.InputStream;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import kny.cook.context.ApplicationContextListener;
import kny.cook.dao.mariadb.BoardDaoImpl;
import kny.cook.dao.mariadb.MemberDaoImpl;
import kny.cook.dao.mariadb.PhotoBoardDaoImpl;
import kny.cook.dao.mariadb.PhotoFileDaoImpl;
import kny.cook.dao.mariadb.RecipeDaoImpl;
import kny.cook.sql.DataSource;
import kny.cook.sql.PlatformTransactionManager;

public class DataLoaderListener implements ApplicationContextListener {

  @Override
  public void contextInitialized(Map<String, Object> context) {
    try {
      // DB 연결 정보
      String jdbcUrl = "jdbc:mariadb://localhost:3306/recipedb";
      String username = "study";
      String password = "1111";

      DataSource dataSource = new DataSource(jdbcUrl, username, password);

      // Mybatis 객체 준비
      InputStream inputStream = Resources.getResourceAsStream("kny/cook/conf/mybatis-config.xml");
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

      context.put("dataSource", dataSource);

      context.put("boardDao", new BoardDaoImpl(sqlSessionFactory));
      context.put("recipeDao", new RecipeDaoImpl(sqlSessionFactory));
      context.put("memberDao", new MemberDaoImpl(sqlSessionFactory));
      context.put("photoBoardDao", new PhotoBoardDaoImpl(sqlSessionFactory));
      context.put("photoFileDao", new PhotoFileDaoImpl(sqlSessionFactory));

      PlatformTransactionManager txManager = new PlatformTransactionManager(dataSource);
      context.put("transactionManager", txManager);


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {

    DataSource dataSource = (DataSource) context.get("dataSource");
    dataSource.clean();
  }
}
