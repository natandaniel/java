package jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
public class JdbcTemplateUpdatingTests {
  @Autowired
  @Qualifier("mysqlJdbcTemplate")
  private JdbcTemplate mysqlJdbcTemplate;

  @BeforeEach
  void setUp() {
    TestUtility.executeSqlScripts(mysqlJdbcTemplate.getDataSource(), "create-tables.sql",
        "populate-tables.sql");
  }

  @AfterEach
  void cleanUp() {
    TestUtility.executeSqlScripts(mysqlJdbcTemplate.getDataSource(), "drop-tables.sql");
  }

  // insertion

  @Test
  void testInsertCoffeeRecord() {
    mysqlJdbcTemplate.update(
        "insert into COFFEES (cof_name, sup_id, price, sales, total) values (?, ?, ?, ?, ?)",
        "Ethiopian", 49, 10.99, 0, 0);

    assertEquals(6, mysqlJdbcTemplate.queryForObject("select count(*) from COFFEES",
        Integer.class));

    Coffee coffee =
        mysqlJdbcTemplate.queryForObject("select cof_name, price from COFFEES where cof_name = ?",
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), resultSet.getFloat("price")),
            "Ethiopian");

    assertNotNull(coffee);
    assertEquals("Ethiopian", coffee.getName());
    assertEquals(10.99f, coffee.getPrice());
  }

  // updating an existing entry

  @Test
  void testUpdateCoffeeRecord() {
    mysqlJdbcTemplate.update(
        "update COFFEES set price = ? where cof_name = ?",
        8.99, "Colombian");

    Coffee coffee =
        mysqlJdbcTemplate.queryForObject("select cof_name, price from COFFEES where cof_name = ?",
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), resultSet.getFloat("price")),
            "Colombian");

    assertNotNull(coffee);
    assertEquals("Colombian", coffee.getName());
    assertEquals(8.99f, coffee.getPrice());
  }

  // deleting an existing entry

  @Test
  void testDeleteCoffeeHouseRecord() {
    mysqlJdbcTemplate.update(
        "delete from COFFEE_HOUSES where store_id = ?", 10023);

    assertEquals(13, mysqlJdbcTemplate.queryForObject("select count(*) from COFFEE_HOUSES",
        Integer.class));
  }

  // batch insertions

  @Test
  void testInsertCoffeeRecordBatch() {
    List<Coffee> coffeeBatch = new ArrayList<>();
    coffeeBatch.add(new Coffee("Coffee1", 1.99f));
    coffeeBatch.add(new Coffee("Coffee2", 1.99f));
    coffeeBatch.add(new Coffee("Coffee3", 1.99f));
    coffeeBatch.add(new Coffee("Coffee4", 1.99f));

    mysqlJdbcTemplate.batchUpdate(
        "insert into COFFEES (cof_name, sup_id, price, sales, total) values (?, ?, ?, ?, ?)",
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            Coffee coffee = coffeeBatch.get(i);
            ps.setString(1, coffee.getName());
            ps.setInt(2, 49);
            ps.setFloat(3, coffee.getPrice());
            ps.setInt(4, 0);
            ps.setInt(5, 0);
          }

          @Override
          public int getBatchSize() {
            return coffeeBatch.size();
          }
        });

    assertEquals(9, mysqlJdbcTemplate.queryForObject("select count(*) from COFFEES",
        Integer.class));

    Coffee coffee =
        mysqlJdbcTemplate.queryForObject("select cof_name, price from COFFEES where cof_name = ?",
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), resultSet.getFloat("price")),
            "Coffee3");

    assertNotNull(coffee);
    assertEquals("Coffee3", coffee.getName());
    assertEquals(1.99f, coffee.getPrice());
  }

}
