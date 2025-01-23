package jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
public class JdbcClientUpdatingTests {
  @Autowired
  private MysqlDataSource mysqlDataSource;

  @Autowired
  @Qualifier("mysqlJdbcClient")
  private JdbcClient mysqlJdbcClient;

  @BeforeEach
  void setUp() {
    TestUtility.executeSqlScripts(mysqlDataSource,
        "create-tables.sql", "populate-tables.sql");
  }

  @AfterEach
  void cleanUp() {
    TestUtility.executeSqlScripts(mysqlDataSource, "drop-tables.sql");
  }

  // insertion

  @Test
  void testInsertCoffeeRecord() {
    mysqlJdbcClient.sql("insert into COFFEES (cof_name, sup_id, price, sales, total) values " +
                       "(:name, :sup_id, :price, :sales, :total)")
                   .param("name", "Ethiopian")
                   .param("sup_id", 49)
                   .param("price", 10.99)
                   .param("sales", 0)
                   .param("total", 0)
                   .update();

    assertEquals(6,
        mysqlJdbcClient.sql("select count(*) from COFFEES").query(Integer.class).single());

    Coffee coffee =
        mysqlJdbcClient.sql("select cof_name, price from COFFEES where cof_name = :name")
                       .param("name", "Ethiopian")
                       .query((resultSet, rowNum) ->
                           new Coffee(resultSet.getString("cof_name"), 49,
                               resultSet.getFloat("price"), 0, 0))
                       .single();

    assertNotNull(coffee);
    assertEquals("Ethiopian", coffee.name());
    assertEquals(10.99f, coffee.price());
  }

  // updating an existing entry

  @Test
  void testUpdateCoffeeRecord() {
    mysqlJdbcClient.sql("update COFFEES set price = ? where cof_name = ?")
                   .param(8.99)
                   .param("Colombian")
                   .update();

    Coffee coffee =
        mysqlJdbcClient.sql("select cof_name, price from COFFEES where cof_name = ?")
                       .param("Colombian")
                       .query((resultSet, rowNum) ->
                           new Coffee(resultSet.getString("cof_name"), 49,
                               resultSet.getFloat("price"), 0, 0))
                       .single();

    assertNotNull(coffee);
    assertEquals("Colombian", coffee.name());
    assertEquals(8.99f, coffee.price(), 0.01);
  }

  // deleting an existing entry

  @Test
  void testDeleteCoffeeHouseRecord() {
    mysqlJdbcClient.sql("delete from COFFEE_HOUSES where store_id = :id")
                   .param("id", 10023)
                   .update();

    assertEquals(13,
        mysqlJdbcClient.sql("select count(*) from COFFEE_HOUSES").query(Integer.class).single());
  }

}
