package jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MultiDataSourceJdbcTemplateConfiguration.class })
class JdbcTemplateTest {
  @Autowired
  @Qualifier("mysql")
  private JdbcTemplate mysqlJdbcTemplate;

  @BeforeEach
  void initData() {
    createAndPopulateTables(mysqlJdbcTemplate.getDataSource());
  }

  private void createAndPopulateTables(DataSource dataSource) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScripts(
        new ClassPathResource("create-tables.sql"),
        new ClassPathResource("populate-tables.sql"));
    populator.execute(dataSource);
  }

  @AfterEach
  void cleanUpData() {
    dropTables(mysqlJdbcTemplate.getDataSource());
  }

  private void dropTables(DataSource dataSource) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScripts(new ClassPathResource("drop-tables.sql"));
    populator.execute(dataSource);
  }

  @Test
  void testToto() {
    System.out.println("hello");
  }

}
