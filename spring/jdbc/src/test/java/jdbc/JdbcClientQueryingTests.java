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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll and @AfterAll
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
class JdbcClientQueryingTests {
  @Autowired
  private MysqlDataSource mysqlDataSource;

  @Autowired
  @Qualifier("mysqlJdbcClient")
  private JdbcClient mysqlJdbcClient;

  @BeforeAll
  void setUp() {
    TestUtility.executeSqlScripts(mysqlDataSource,
        "create-tables.sql", "populate-tables.sql");
  }

  @AfterAll
  void cleanUp() {
    TestUtility.executeSqlScripts(mysqlDataSource, "drop-tables.sql");
  }

  // getting row counts in the relations

  @Test
  void testCountOfSuppliersTable() {
    assertEquals(5,
        mysqlJdbcClient.sql("select count(*) from SUPPLIERS").query(Integer.class).single());
  }

  @Test
  void testCountOfCoffeesTable() {
    assertEquals(5,
        mysqlJdbcClient.sql("select count(*) from COFFEES").query(Integer.class).single());
  }

  @Test
  void testCountOfCofInventoryTable() {
    assertEquals(4,
        mysqlJdbcClient.sql("select count(*) from COF_INVENTORY").query(Integer.class).single());
  }

  @Test
  void testCountOfMerchInventoryTable() {
    assertEquals(12,
        mysqlJdbcClient.sql("select count(*) from MERCH_INVENTORY").query(Integer.class).single());
  }

  @Test
  void testCountOfCoffeeHousesTable() {
    assertEquals(14,
        mysqlJdbcClient.sql("select count(*) from COFFEE_HOUSES").query(Integer.class).single());
  }

  // getting row counts in the relations using a bind variable or a named parameter

  @Test
  void testSuppliersCountWithZip93966() {
    assertEquals(2,
        mysqlJdbcClient.sql("select count(*) from SUPPLIERS where zip = ?")
                       .param(93966)
                       .query(Integer.class)
                       .single());
  }

  @Test
  void testCoffeesCountWithSupId101() {
    assertEquals(2,
        mysqlJdbcClient.sql("select count(*) from COFFEES where sup_id = ?")
                       .param(101)
                       .query(Integer.class)
                       .single());
  }

  @Test
  void testCofInventoryCountWithSupId101() {
    assertEquals(2,
        mysqlJdbcClient.sql("select count(*) from COF_INVENTORY where sup_id = :id")
                       .param("id", 101)
                       .query(Integer.class)
                       .single());
  }

  @Test
  void testMerchInventoryCountWithSupId456() {
    assertEquals(7,
        mysqlJdbcClient.sql("select count(*) from MERCH_INVENTORY where sup_id = :id")
                       .param("id", 456)
                       .query(Integer.class)
                       .single());
  }

  @Test
  void testCoffeeHousesCountWithCitySF() {
    assertEquals(3,
        mysqlJdbcClient.sql("select count(*) from COFFEE_HOUSES where city = :city_shortname")
                       .param("city_shortname", "SF")
                       .query(Integer.class)
                       .single());
  }

  // getting a String

  @Test
  void testCityOfCoffeeHouseWithStoreId10023() {
    assertEquals("Mendocino",
        mysqlJdbcClient.sql("select city from COFFEE_HOUSES where store_id = ?")
                       .param(10023)
                       .query(String.class)
                       .single());
  }

  // populating domain objects

  @Test
  void testRetrieveAndPopulateCoffeeByName() {
    Coffee coffee =
        mysqlJdbcClient.sql("select cof_name, price from COFFEES where cof_name = :name")
                       .param("name", "Colombian")
                       .query((resultSet, rowNum) ->
                           new Coffee(resultSet.getString("cof_name"), 49,
                               resultSet.getFloat("price"), 0, 0))
                       .single();

    assertNotNull(coffee);
    assertEquals("Colombian", coffee.getName());
    assertEquals(7.99, coffee.getPrice(), 0.01);
  }

  @Test
  void testRetrieveAndPopulateCoffeeList() {
    List<Coffee> coffees =
        mysqlJdbcClient.sql("select cof_name, price from COFFEES")
                       .query((resultSet, rowNum) ->
                           new Coffee(resultSet.getString("cof_name"), 49,
                               resultSet.getFloat("price"), 0, 0))
                       .list();

    assertNotNull(coffees);
    assertEquals(5, coffees.size());
  }

}
