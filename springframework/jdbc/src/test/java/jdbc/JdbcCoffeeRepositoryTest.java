package jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll and @AfterAll
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
public class JdbcCoffeeRepositoryTest {
  @Autowired
  private MysqlDataSource mysqlDataSource;
  @Autowired
  @Qualifier("mysqlJdbcClient")
  private JdbcClient mysqlJdbcClient;
  @Autowired
  private JdbcCoffeeRepository jdbcCoffeeRepository;

  @BeforeAll
  void setUp() {
    TestUtility.executeSqlScripts(mysqlDataSource, "create-tables.sql", "populate-tables.sql");
  }

  @AfterAll
  void cleanUp() {
    TestUtility.executeSqlScripts(mysqlDataSource, "drop-tables.sql");
  }

  @Test
  void testInsertCoffeeRecord() {
    jdbcCoffeeRepository.insert(new Coffee("Ethiopian", 49, 10.99f, 0, 0));

    assertEquals(6,
        mysqlJdbcClient.sql("select count(*) from COFFEES").query(Integer.class).single());

    Coffee coffee =
        mysqlJdbcClient.sql("select cof_name, sup_id, price, sales, total from COFFEES where " +
                           "cof_name = :name")
                       .param("name", "Ethiopian")
                       .query((resultSet, rowNum) ->
                           new Coffee(resultSet.getString("cof_name"), resultSet.getInt("sup_id"),
                               resultSet.getFloat("price"), resultSet.getInt("sales"),
                               resultSet.getInt("total")))
                       .single();

    assertNotNull(coffee);
    assertEquals("Ethiopian", coffee.getName());
    assertEquals(49, coffee.getSupId());
    assertEquals(10.99f, coffee.getPrice());
    assertEquals(0, coffee.getSales());
    assertEquals(0, coffee.getTotal());
  }
}
