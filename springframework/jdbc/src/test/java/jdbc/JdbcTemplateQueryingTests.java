package jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll and @AfterAll
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MultiDataSourceJdbcTemplateConfiguration.class })
class JdbcTemplateQueryingTests {
  @Autowired
  @Qualifier("mysql")
  private JdbcTemplate mysqlJdbcTemplate;

  @BeforeAll
  void setUp() {
    TestUtility.executeSqlScripts(mysqlJdbcTemplate.getDataSource(), "create-tables.sql",
        "populate-tables.sql");
  }

  @AfterAll
  void cleanUp() {
    TestUtility.executeSqlScripts(mysqlJdbcTemplate.getDataSource(), "drop-tables.sql");
  }

  // getting row counts in the relations

  @Test
  void testCountOfSuppliersTable() {
    assertEquals(5, mysqlJdbcTemplate.queryForObject("select count(*) from SUPPLIERS",
        Integer.class));
  }

  @Test
  void testCountOfCoffeesTable() {
    assertEquals(5, mysqlJdbcTemplate.queryForObject("select count(*) from COFFEES",
        Integer.class));
  }

  @Test
  void testCountOfCofInventoryTable() {
    assertEquals(4, mysqlJdbcTemplate.queryForObject("select count(*) from COF_INVENTORY",
        Integer.class));
  }

  @Test
  void testCountOfMerchInventoryTable() {
    assertEquals(12, mysqlJdbcTemplate.queryForObject("select count(*) from MERCH_INVENTORY",
        Integer.class));
  }

  @Test
  void testCountOfCoffeeHousesTable() {
    assertEquals(14, mysqlJdbcTemplate.queryForObject("select count(*) from COFFEE_HOUSES",
        Integer.class));
  }

  // getting row counts in the relations using a bind variable

  @Test
  void testSuppliersCountWithZip93966() {
    assertEquals(2,
        mysqlJdbcTemplate.queryForObject("select count(*) from SUPPLIERS where zip = ?",
            Integer.class, 93966));
  }

  @Test
  void testCoffeesCountWithSupId101() {
    assertEquals(2,
        mysqlJdbcTemplate.queryForObject("select count(*) from COFFEES where sup_id = ?",
            Integer.class, 101));
  }

  @Test
  void testCofInventoryCountWithSupId101() {
    assertEquals(2,
        mysqlJdbcTemplate.queryForObject("select count(*) from COF_INVENTORY where sup_id = ?",
            Integer.class, 101));
  }

  @Test
  void testMerchInventoryCountWithSupId456() {
    assertEquals(7,
        mysqlJdbcTemplate.queryForObject("select count(*) from MERCH_INVENTORY where sup_id = ?",
            Integer.class, 456));
  }

  @Test
  void testCoffeeHousesCountWithCitySF() {
    assertEquals(3,
        mysqlJdbcTemplate.queryForObject("select count(*) from COFFEE_HOUSES where city = ?",
            Integer.class, "SF"));
  }

  // getting a String

  @Test
  void testCityOfCoffeeHouseWithStoreId10023() {
    assertEquals("Mendocino",
        mysqlJdbcTemplate.queryForObject("select city from COFFEE_HOUSES where store_id = ?",
            String.class, 10023));
  }

  // populating domain objects

  @Test
  void testRetrieveAndPopulateCoffeeByName() {
    Coffee coffee = mysqlJdbcTemplate.queryForObject("select cof_name, price from COFFEES " +
            "where cof_name = ?",
        (resultSet, rowNum) ->
            new Coffee(resultSet.getString("cof_name"), resultSet.getFloat("price")),
        "Colombian");

    assertNotNull(coffee);
    assertEquals("Colombian", coffee.getName());
    assertEquals(7.99, coffee.getPrice(), 0.01);
  }

  @Test
  void testRetrieveAndPopulateCoffeeList() {
    List<Coffee> coffees = mysqlJdbcTemplate.query("select cof_name, price from COFFEES",
        (resultSet, rowNum) ->
            new Coffee(resultSet.getString("cof_name"), resultSet.getFloat("price")));

    assertNotNull(coffees);
    assertEquals(5, coffees.size());
  }

}
