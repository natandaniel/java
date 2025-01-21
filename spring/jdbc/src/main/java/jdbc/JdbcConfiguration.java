package jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.JdbcClient;

@Configuration
@ComponentScan("jdbc")
@PropertySource("classpath:jdbc/database.properties")
public class JdbcConfiguration {
  @Value("${mysql.host}")
  private String mysqlDatabaseHost;
  @Value("${mysql.dbname}")
  private String mysqlDatabaseName;
  @Value("${mysql.user}")
  private String mysqlDatabaseUser;
  @Value("${mysql.password}")
  private String mysqlDatabaseUserPassword;

  @Bean
  @Qualifier("mysqlNamedParameterJdbcTemplate")
  public NamedParameterJdbcTemplate mysqlNamedParameterJdbcTemplate() {
    return new NamedParameterJdbcTemplate(mysqlDataSource());
  }

  @Bean
  @Qualifier("mysqlJdbcTemplate")
  public JdbcTemplate mysqlJdbcTemplate() {
    return new JdbcTemplate(mysqlDataSource());
  }

  @Bean
  @Qualifier("mysqlJdbcClient")
  public JdbcClient mysqlJdbcClient() {
    return JdbcClient.create(mysqlDataSource());
  }

  @Bean
  public MysqlDataSource mysqlDataSource() {
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setServerName(mysqlDatabaseHost);
    mysqlDataSource.setDatabaseName(mysqlDatabaseName);
    mysqlDataSource.setUser(mysqlDatabaseUser);
    mysqlDataSource.setPassword(mysqlDatabaseUserPassword);
    return mysqlDataSource;
  }

}
