package jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
public class NamedParameterJdbcTemplateUpdatingTests {
  @Autowired
  @Qualifier("mysqlNamedParameterJdbcTemplate")
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  @BeforeEach
  void setUp() {
    TestUtility.executeSqlScripts(namedParameterJdbcTemplate.getJdbcTemplate().getDataSource(),
        "create-tables.sql", "populate-tables.sql");
  }

  @AfterEach
  void cleanUp() {
    TestUtility.executeSqlScripts(namedParameterJdbcTemplate.getJdbcTemplate().getDataSource(),
        "drop-tables.sql");
  }

  // insertion

  @Test
  void testInsertCoffeeRecord() {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("name", "Ethiopian");
    parameters.addValue("sup_id", 49);
    parameters.addValue("price", 10.99);
    parameters.addValue("sales", 0);
    parameters.addValue("total", 0);

    namedParameterJdbcTemplate.update(
        "insert into COFFEES (cof_name, sup_id, price, sales, total) values (:name, :sup_id, " +
            ":price, :sales, :total)",
        parameters);

    assertEquals(6, namedParameterJdbcTemplate.queryForObject("select count(*) from COFFEES",
        EmptySqlParameterSource.INSTANCE,
        Integer.class));

    Coffee coffee =
        namedParameterJdbcTemplate.queryForObject(
            "select cof_name, price from COFFEES where cof_name = :name",
            new MapSqlParameterSource("name", "Ethiopian"),
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), 49, resultSet.getFloat("price"), 0, 0));

    assertNotNull(coffee);
    assertEquals("Ethiopian", coffee.getName());
    assertEquals(10.99f, coffee.getPrice());
  }

  // updating an existing entry

  @Test
  void testUpdateCoffeeRecord() {
    MapSqlParameterSource parameters = new MapSqlParameterSource();
    parameters.addValue("name", "Colombian");
    parameters.addValue("price", 8.99);

    namedParameterJdbcTemplate.update(
        "update COFFEES set price = :price where cof_name = :name", parameters);

    Coffee coffee =
        namedParameterJdbcTemplate.queryForObject(
            "select cof_name, price from COFFEES where cof_name = :name",
            new MapSqlParameterSource("name", "Colombian"),
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), 49, resultSet.getFloat("price"), 0, 0));

    assertNotNull(coffee);
    assertEquals("Colombian", coffee.getName());
    assertEquals(8.99f, coffee.getPrice());
  }

  // deleting an existing entry

  @Test
  void testDeleteCoffeeHouseRecord() {
    namedParameterJdbcTemplate.update(
        "delete from COFFEE_HOUSES where store_id = :id", new MapSqlParameterSource("id", 10023));

    assertEquals(13, namedParameterJdbcTemplate.queryForObject("select count(*) from COFFEE_HOUSES",
        EmptySqlParameterSource.INSTANCE,
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

    namedParameterJdbcTemplate.batchUpdate(
        "insert into COFFEES (cof_name, sup_id, price, sales, total) values (:name, :supId, " +
            ":price, :sales, :total)",
        SqlParameterSourceUtils.createBatch(coffeeBatch));

    assertEquals(9, namedParameterJdbcTemplate.queryForObject("select count(*) from COFFEES",
        EmptySqlParameterSource.INSTANCE,
        Integer.class));

    Coffee coffee =
        namedParameterJdbcTemplate.queryForObject(
            "select cof_name, price from COFFEES where cof_name = :name",
            new MapSqlParameterSource("name", "Coffee3"),
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), 49, resultSet.getFloat("price"), 0, 0));

    assertNotNull(coffee);
    assertEquals("Coffee3", coffee.getName());
    assertEquals(1.99f, coffee.getPrice());
  }

}
