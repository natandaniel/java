package jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.mapping.event.RelationalEvent;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableJdbcRepositories("jdbc")
@PropertySource("classpath:database.properties")
class JdbcConfiguration extends AbstractJdbcConfiguration {
  @Value("${mysql.host}")
  private String mysqlDatabaseHost;
  @Value("${mysql.dbname}")
  private String mysqlDatabaseName;
  @Value("${mysql.user}")
  private String mysqlDatabaseUser;
  @Value("${mysql.password}")
  private String mysqlDatabaseUserPassword;

  @Bean
  public NamedParameterJdbcOperations mysqlNamedParameterJdbcTemplate(DataSource dataSource) {
    return new NamedParameterJdbcTemplate(dataSource);
  }

  @Bean
  TransactionManager transactionManager() {
    return new DataSourceTransactionManager(mysqlDataSource());
  }

  @Bean
  public DataSource mysqlDataSource() {
    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setServerName(mysqlDatabaseHost);
    mysqlDataSource.setDatabaseName(mysqlDatabaseName);
    mysqlDataSource.setUser(mysqlDatabaseUser);
    mysqlDataSource.setPassword(mysqlDatabaseUserPassword);
    return mysqlDataSource;
  }

  @Override
  protected List<?> userConverters() {
    return List.of(new StringToCoffeeMakerTypeConverter());
  }

  @Bean
  public ApplicationListener<?> loggingListener() {
    return (ApplicationListener<ApplicationEvent>) event -> {
      if (event instanceof RelationalEvent) {
        LoggerFactory.getLogger(JdbcConfiguration.class).info("Received an event: {}", event);
      }
    };
  }

  @Bean
  public BeforeSaveCallback<CoffeeHouse> timeStampingSaveTime() {
    return (entity, aggregateChange) -> {
      LoggerFactory.getLogger(JdbcConfiguration.class).info("saving coffee house");
      return entity;
    };
  }

}
