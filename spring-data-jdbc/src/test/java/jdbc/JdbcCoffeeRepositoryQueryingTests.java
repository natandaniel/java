package jdbc;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // This allows non-static @BeforeAll and @AfterAll
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
public class JdbcCoffeeRepositoryQueryingTests {
  @Autowired
  DataSource dataSource;
  @Autowired
  private JdbcCoffeeRepository jdbcCoffeeRepository;

  @BeforeAll
  void setUp() {
    TestUtility.executeSqlScripts(dataSource, "create-tables.sql", "populate-tables.sql");
  }

  @AfterAll
  void cleanUp() {
    TestUtility.executeSqlScripts(dataSource, "drop-tables.sql");
  }

  @Test
  void testCountOfCoffeesTable() {
    assertEquals(5, jdbcCoffeeRepository.count());
  }

  @Test
  void testCoffeesCountWithSupId101() {
    assertEquals(2, jdbcCoffeeRepository.countBySupId(101));
  }

  @Test
  void testRetrieveAndPopulateCoffeeByName() {
    try {
      Coffee coffee = jdbcCoffeeRepository.findById("Colombian").orElseThrow();

      assertEquals("Colombian", coffee.name());
      assertEquals(101, coffee.supId());
      assertEquals(7.99f, coffee.price());
      assertEquals(0, coffee.sales());
      assertEquals(0, coffee.total());
    }
    catch (Exception e) {
      fail(e.getMessage(), e.getCause());
    }
  }

  @Test
  void testRetrieveAndProjectNameAndSupIdOfCoffeeByName() {
    CoffeeNameAndSupId coffeeNameAndSupId =
        jdbcCoffeeRepository.getCoffeeNameAndSupIdByName("Colombian");
    assertEquals("Colombian", coffeeNameAndSupId.getName());
    assertEquals(101, coffeeNameAndSupId.getSupId());
  }

  @Test
  void testRetrieveAndPopulateCoffeeList() {
    List<Coffee> coffees = jdbcCoffeeRepository.findAll();

    assertFalse(coffees.isEmpty());
    assertEquals(5, coffees.size());
  }
}
