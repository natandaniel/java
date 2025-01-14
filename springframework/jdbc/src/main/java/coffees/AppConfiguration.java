package coffees;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Configuration
@ComponentScan("coffees")
@PropertySource("classpath:coffees/app.properties")
public class AppConfiguration {
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
    JdbcTemplate jdbcTemplate = new JdbcTemplate(mysqlDataSource());
    initDatabaseAndTablesIfAbsent(jdbcTemplate);
    return jdbcTemplate;
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

  private static void initDatabaseAndTablesIfAbsent(JdbcTemplate jdbcTemplate) throws IOException {
    try (InputStream inputStream = AppConfiguration.class.getClassLoader()
                                                         .getResourceAsStream(
                                                             "coffees/create-tables.sql");
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

      StringBuilder sqlBuilder = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        sqlBuilder.append(line).append(System.lineSeparator());
      }

      String[] sqlStatements = sqlBuilder.toString().split(";");

      for (String statement : sqlStatements) {
        if (!statement.trim().isEmpty()) {
          jdbcTemplate.execute(statement.trim());
        }
      }
    }
  }
}
