package kny.cook;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement

@Configuration

@PropertySource("classpath:kny/cook/conf/jdbc.properties")
public class DatabaseConfig {


  @Value("${jdbc.driver}")
  String jdbcDriver;

  @Value("${jdbc.url}")
  String jdbcUrl;

  @Value("${jdbc.username}")
  String jdbcUsername;

  @Value("${jdbc.password}")
  String jdbcPassword;

  public DatabaseConfig() {
    System.out.println("DatabaseConfig 객체 생성!");
  }

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
  public PlatformTransactionManager TransactionManger(
      // 필요한 값이 있다면 이렇게 파라미터로 선언만 하라.
      // 단 IoC 컨테이너에 들어 있는 값이어야 한다.
      DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

}
