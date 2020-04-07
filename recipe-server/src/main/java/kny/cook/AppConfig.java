package kny.cook;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

// Spring IoC 컨테이너가 탐색할 패키지 설정
// => 지정한 패키지 및 하위 패키지를 모두 뒤져서
// @Component 애노테이션이 붙은 클래스를 찾아 객체를 생성한다.
//
@ComponentScan(value = "kny.cook")

// Spring IoC 컨테이너에서 사용할 Properties 파일 로딩
@PropertySource("classpath:kny/cook/conf/jdbc.properties")

// Mybatis DAO 프록시를 자동 생성할 인터페이스 지정
@MapperScan("kny.cook.dao")
public class AppConfig {

  // @PropertySource로 로딩한 .properties 파일의 값을 사용하고 싶다면,
  // 다음 애노테이션을 인스턴스 필드 앞에 붙여라.
  // Spring IoC 컨테이너가 이 클래스의 객체를 생성할 때
  // 해당 필드의 프로퍼티 값을 자동으로 주입할 것이다.
  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.username}")
  String jdbcUsername;

  @Value("${jdbc.password}")
  String jdbcPassword;

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource ds = new DriverManagerDataSource();
    ds.setDriverClassName(jdbcDriver);
    ds.setUrl(jdbcUrl);
    ds.setUsername(jdbcUsername);
    ds.setPassword(jdbcPassword);
    return ds;
  }


  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext appCtx)
      throws Exception {

    SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
    sqlSessionFactoryBean.setDataSource(dataSource);
    sqlSessionFactoryBean.setTypeAliasesPackage("kny.cook.domain");
    sqlSessionFactoryBean
        .setMapperLocations(appCtx.getResources("classpath:kny/cook/mapper/*Mapper.xml"));


    return sqlSessionFactoryBean.getObject();
  }



  @Bean
  public PlatformTransactionManager TransactionManger(
      // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라.
      // 단 IoC 컨테이너에 들어 있는 값이어야 한다.
      DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }



}
