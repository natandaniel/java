package jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll and @AfterAll
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
class NamedParameterJdbcTemplateQueryingTests {
  @Autowired
  @Qualifier("mysqlNamedParameterJdbcTemplate")
  private NamedParameterJdbcTemplate mysqlNamedParameterJdbcTemplate;

  @BeforeAll
  void setUp() {
    TestUtility.executeSqlScripts(mysqlNamedParameterJdbcTemplate.getJdbcTemplate().getDataSource(),
        "create-tables.sql", "populate-tables.sql");
  }

  @AfterAll
  void cleanUp() {
    TestUtility.executeSqlScripts(mysqlNamedParameterJdbcTemplate.getJdbcTemplate().getDataSource(),
        "drop-tables.sql");
  }

  // getting row counts in the relations using a parameter

  @Test
  void testSuppliersCountWithZip93966() {
    assertEquals(2,
        mysqlNamedParameterJdbcTemplate.queryForObject(
            "select count(*) from SUPPLIERS where zip = :zip_code",
            new MapSqlParameterSource("zip_code", 93966),
            Integer.class));
  }

  @Test
  void testCoffeesCountWithSupId101() {
    assertEquals(2,
        mysqlNamedParameterJdbcTemplate.queryForObject(
            "select count(*) from COFFEES where sup_id = :id",
            new MapSqlParameterSource("id", 101),
            Integer.class));
  }

  @Test
  void testCofInventoryCountWithSupId101() {
    assertEquals(2,
        mysqlNamedParameterJdbcTemplate.queryForObject(
            "select count(*) from COF_INVENTORY where sup_id = :id",
            new MapSqlParameterSource("id", 101),
            Integer.class));
  }

  @Test
  void testMerchInventoryCountWithSupId456() {
    assertEquals(7,
        mysqlNamedParameterJdbcTemplate.queryForObject(
            "select count(*) from MERCH_INVENTORY where sup_id = :id",
            new MapSqlParameterSource("id", 456),
            Integer.class));
  }

  @Test
  void testCoffeeHousesCountWithCitySF() {
    assertEquals(3,
        mysqlNamedParameterJdbcTemplate.queryForObject(
            "select count(*) from COFFEE_HOUSES where city = :city_shortname",
            new MapSqlParameterSource("city_shortname", "SF"),
            Integer.class));
  }

  // getting a String

  @Test
  void testCityOfCoffeeHouseWithStoreId10023() {
    assertEquals("Mendocino",
        mysqlNamedParameterJdbcTemplate.queryForObject(
            "select city from COFFEE_HOUSES where store_id = :id",
            new MapSqlParameterSource("id", 10023),
            String.class));
  }

  // populating domain objects

  @Test
  void testRetrieveAndPopulateCoffeeByName() {
    Coffee coffee =
        mysqlNamedParameterJdbcTemplate.queryForObject(
            "select cof_name, price from COFFEES where cof_name = :name",
            new BeanPropertySqlParameterSource(new Coffee("Colombian", 7.99f)),
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), resultSet.getFloat("price"))
        );

    assertNotNull(coffee);
    assertEquals("Colombian", coffee.getName());
    assertEquals(7.99, coffee.getPrice(), 0.01);
  }

  @Test
  void testRetrieveAndPopulateCoffeeList() {
    List<Coffee> coffees =
        mysqlNamedParameterJdbcTemplate.query("select cof_name, price from COFFEES",
            (resultSet, rowNum) ->
                new Coffee(resultSet.getString("cof_name"), resultSet.getFloat("price")));

    assertNotNull(coffees);
    assertEquals(5, coffees.size());
  }

}
