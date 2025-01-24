package jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
@Sql({ "/create-tables.sql", "/populate-tables.sql" }) // Load schema and data before each test
@Sql(scripts = "/drop-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CoffeeBeansRepositoryTests {
  @Autowired
  private CoffeeBeansRepository coffeeBeansRepository;

  @Test
  void testInsertingANewCoffeeBeansProduct() {
    Product product = Product.builder("Mountain Roast", 10.99f)
                             .setWeight(500.0f)
                             .setSupplierId((short) 1)
                             .build();

    CoffeeBeans coffeeBeans = new CoffeeBeans((short) 0, product, CoffeeBeanType.ARABICA);
    CoffeeBeans savedCoffeeBeans = coffeeBeansRepository.save(coffeeBeans);
    System.out.println("h");
  }

}
