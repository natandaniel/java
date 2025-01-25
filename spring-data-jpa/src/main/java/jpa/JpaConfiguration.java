package jpa;

import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("jpa")
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
class JpaConfiguration {
  @Value("${mysql.host}")
  private String mysqlDatabaseHost;
  @Value("${mysql.dbname}")
  private String mysqlDatabaseName;
  @Value("${mysql.user}")
  private String mysqlDatabaseUser;
  @Value("${mysql.password}")
  private String mysqlDatabaseUserPassword;

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("jpa");
    factory.setDataSource(dataSource);

    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    factory.setJpaProperties(jpaProperties);

    return factory;
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

  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }

}
