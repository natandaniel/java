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
                new Coffee(resultSet.getString("cof_name"), 49, resultSet.getFloat("price"), 0, 0),
            "Ethiopian");

    assertNotNull(coffee);
    assertEquals("Ethiopian", coffee.name());
    assertEquals(10.99f, coffee.price());
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
                new Coffee(resultSet.getString("cof_name"), 49, resultSet.getFloat("price"), 0, 0),
            "Colombian");

    assertNotNull(coffee);
    assertEquals("Colombian", coffee.name());
    assertEquals(8.99f, coffee.price());
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
    coffeeBatch.add(new Coffee("Coffee1", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee2", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee3", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee4", 49, 1.99f, 0, 0));

    mysqlJdbcTemplate.batchUpdate(
        "insert into COFFEES (cof_name, sup_id, price, sales, total) values (?, ?, ?, ?, ?)",
        new BatchPreparedStatementSetter() {
          @Override
          public void setValues(PreparedStatement ps, int i) throws SQLException {
            Coffee coffee = coffeeBatch.get(i);
            ps.setString(1, coffee.name());
            ps.setInt(2, 49);
            ps.setFloat(3, coffee.price());
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
                new Coffee(resultSet.getString("cof_name"), 49, resultSet.getFloat("price"), 0, 0),
            "Coffee3");

    assertNotNull(coffee);
    assertEquals("Coffee3", coffee.name());
    assertEquals(1.99f, coffee.price());
  }

  @Test
  void testInsertCoffeeRecordBatchV2() {
    List<Coffee> coffeeBatch = new ArrayList<>();
    coffeeBatch.add(new Coffee("Coffee1", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee2", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee3", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee4", 49, 1.99f, 0, 0));

    List<Object[]> batch = new ArrayList<>();
    coffeeBatch.forEach(coffee -> batch.add(new Object[] {
        coffee.name(),
        coffee.supId(),
        coffee.price(),
        coffee.sales(),
        coffee.total() }));

    mysqlJdbcTemplate.batchUpdate(
        "insert into COFFEES (cof_name, sup_id, price, sales, total) values (?, ?, ?, ?, ?)",
        batch);

    assertEquals(9, mysqlJdbcTemplate.queryForObject("select count(*) from COFFEES",
        Integer.class));

    Coffee coffee =
        mysqlJdbcTemplate.queryForObject("select cof_name, price from COFFEES where cof_name = ?",
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), 49, resultSet.getFloat("price"), 0, 0),
            "Coffee3");

    assertNotNull(coffee);
    assertEquals("Coffee3", coffee.name());
    assertEquals(1.99f, coffee.price());
  }

  @Test
  void testInsertCoffeeRecordMultipleBatches() {
    List<Coffee> coffeeBatch = new ArrayList<>();
    coffeeBatch.add(new Coffee("Coffee1", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee2", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee3", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee4", 49, 1.99f, 0, 0));

    mysqlJdbcTemplate.batchUpdate(
        "insert into COFFEES (cof_name, sup_id, price, sales, total) values (?, ?, ?, ?, ?)",
        coffeeBatch,
        2,
        (PreparedStatement ps, Coffee coffee) -> {
          ps.setString(1, coffee.name());
          ps.setInt(2, 49);
          ps.setFloat(3, coffee.price());
          ps.setInt(4, 0);
          ps.setInt(5, 0);
        }
    );

    assertEquals(9, mysqlJdbcTemplate.queryForObject("select count(*) from COFFEES",
        Integer.class));

    Coffee coffee =
        mysqlJdbcTemplate.queryForObject("select cof_name, price from COFFEES where cof_name = ?",
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), 49, resultSet.getFloat("price"), 0, 0),
            "Coffee3");

    assertNotNull(coffee);
    assertEquals("Coffee3", coffee.name());
    assertEquals(1.99f, coffee.price());
  }

}
