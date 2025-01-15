package coffees;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.io.IOException;

@Configuration
@ComponentScan("coffees")
@PropertySource("classpath:coffees/app.properties")
public class CoffeesAppConfig {
  @Autowired
  private MysqlDataSource mysqlDataSource;

  @Value("${mysql.host}")
  private String mysqlDatabaseHost;
  @Value("${mysql.dbname}")
  private String mysqlDatabaseName;
  @Value("${mysql.user}")
  private String mysqlDatabaseUser;
  @Value("${mysql.password}")
  private String mysqlDatabaseUserPassword;

  @Bean
  @Qualifier("mysql")
  public JdbcTemplate mysqlJdbcTemplate() throws IOException {
    return new JdbcTemplate(mysqlDataSource());
  }

  @Bean(initMethod = "createAndPopulateTables", destroyMethod = "dropTables")
  public MysqlDataSource mysqlDataSource() {
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setServerName(mysqlDatabaseHost);
    mysqlDataSource.setDatabaseName(mysqlDatabaseName);
    mysqlDataSource.setUser(mysqlDatabaseUser);
    mysqlDataSource.setPassword(mysqlDatabaseUserPassword);
    return mysqlDataSource;
  }

  private void createAndPopulateTables() {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScripts(
        new ClassPathResource("coffees/create-tables.sql"),
        new ClassPathResource("coffees/populate-tables.sql"));
    populator.execute(mysqlDataSource);
  }

  private void dropTables() {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScripts(new ClassPathResource("coffees/drop-tables.sql"));
    populator.execute(mysqlDataSource);
  }

}
