package kny.cook;

import javax.sql.DataSource;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("kny.cook.dao")
public class MybatisConfig {

  static Logger logger = LogManager.getLogger(MybatisConfig.class);

  public MybatisConfig() {
    logger.debug("MybatisConfig 객체 생성!");
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx)
      throws Exception {

    LogFactory.useLog4JLogging();

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("kny.cook.domain");
    sqlSessionFactoryBean
        .setMapperLocations(appCtx.getResources("classpath:kny/cook/mapper/*Mapper.xml"));

    return sqlSessionFactoryBean.getObject();
  }
}
