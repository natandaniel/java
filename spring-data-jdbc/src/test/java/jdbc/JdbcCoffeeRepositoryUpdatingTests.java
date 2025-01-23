package jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
public class JdbcCoffeeRepositoryUpdatingTests {
  @Autowired
  DataSource dataSource;
  @Autowired
  private JdbcCoffeeRepository jdbcCoffeeRepository;

  @BeforeEach
  void setUp() {
    TestUtility.executeSqlScripts(dataSource, "create-tables.sql", "populate-tables.sql");
  }

  @AfterEach
  void cleanUp() {
    TestUtility.executeSqlScripts(dataSource, "drop-tables.sql");
  }

  @Test
  void testInsertCoffeeRecord() {
    Coffee ethiopianCoffee = new Coffee("Ethiopian", 49, 10.99f, 0, 0);
    jdbcCoffeeRepository.insert(ethiopianCoffee);

    assertEquals(6, jdbcCoffeeRepository.count());

    Coffee actualCoffee = jdbcCoffeeRepository.findById("Ethiopian").orElse(null);

    assertNotNull(actualCoffee);
    assertEquals(ethiopianCoffee.name(), actualCoffee.name());
    assertEquals(ethiopianCoffee.price(), actualCoffee.price());
  }

  // updating an existing entry

  @Test
  void testUpdateCoffeeRecord() {
    Coffee updatedColombianCoffee = new Coffee("Colombian", 49, 8.99f, 0, 0);
    jdbcCoffeeRepository.save(updatedColombianCoffee);

    Coffee actualCoffee = jdbcCoffeeRepository.findById("Colombian").orElse(null);

    assertNotNull(actualCoffee);
    assertEquals(updatedColombianCoffee.name(), actualCoffee.name());
    assertEquals(updatedColombianCoffee.price(), actualCoffee.price());
  }

  // batch insertions

  @Test
  void testInsertCoffeeRecordBatch() {
    List<Coffee> coffeeBatch = new ArrayList<>();
    coffeeBatch.add(new Coffee("Coffee1", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee2", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee3", 49, 1.99f, 0, 0));
    coffeeBatch.add(new Coffee("Coffee4", 49, 1.99f, 0, 0));

    jdbcCoffeeRepository.insertAll(coffeeBatch);

    assertEquals(9, jdbcCoffeeRepository.count());

    Coffee coffee = jdbcCoffeeRepository.findById("Coffee3").orElse(null);

    assertNotNull(coffee);
    assertEquals("Coffee3", coffee.name());
    assertEquals(1.99f, coffee.price());
  }

}
