package jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
@Sql({ "/create-tables.sql", "/populate-tables.sql" })
@Sql(scripts = "/drop-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CoffeeHouseRepositoryTests {
  @Autowired
  private CoffeeHouseRepository coffeeHouseRepository;

  @Test
  void testFindingCoffeeHouseByName() {
    CoffeeHouse coffeeHouse = coffeeHouseRepository.findByName("Relaxing Coffee");
    assertNotNull(coffeeHouse);
    assertEquals("Relaxing Coffee", coffeeHouse.getName());
  }

  @Test
  void testAddingANewCoffeeBeansProductToCoffeeHouseNamedRelaxingCoffee() {
    CoffeeHouse coffeeHouse = coffeeHouseRepository.findByName("Relaxing Coffee");

    coffeeHouse.addCoffeeBeans(new Product("Fabulous Roast", 45.99f, "a premium quality dark " +
        "roast", 500.00f), CoffeeBeanType.ARABICA, 100);

    coffeeHouseRepository.save(coffeeHouse);

    assertTrue(coffeeHouseRepository.findByName("Relaxing Coffee").getCoffeeBeans("Fabulous " +
        "Roast").isPresent());
  }

  @Test
  void testRestockingACoffeeBeansProductForCoffeeHouseNamedRelaxingCoffee() {
    CoffeeHouse coffeeHouse = coffeeHouseRepository.findByName("Relaxing Coffee");
    coffeeHouse.restockCoffeeBeans("Mountain Roast", 50);

    coffeeHouseRepository.save(coffeeHouse);

    CoffeeHouse relaxingCoffee = coffeeHouseRepository.findByName("Relaxing Coffee");
    CoffeeBeans coffeeBeans = relaxingCoffee.getCoffeeBeans("Mountain Roast")
                                            .orElseThrow();

    assertEquals(100, coffeeBeans.stockLevel());
  }

  @Test
  void testSellingACoffeeBeansProductFromCoffeeHouseNamedRelaxingCoffee() {
    CoffeeHouse coffeeHouse = coffeeHouseRepository.findByName("Relaxing Coffee");
    coffeeHouse.sellCoffeeBeans("Mountain Roast", 20);

    coffeeHouseRepository.save(coffeeHouse);

    CoffeeHouse relaxingCoffee = coffeeHouseRepository.findByName("Relaxing Coffee");
    CoffeeBeans coffeeBeans = relaxingCoffee.getCoffeeBeans("Mountain Roast")
                                            .orElseThrow();

    // checking consistent inventory
    assertEquals(30, coffeeBeans.stockLevel());

    // checking consistent sales record
    Sale sale = relaxingCoffee.getLatestSale().orElseThrow();
    assertEquals("Mountain Roast", sale.productName());
    assertEquals(20, sale.quantity());
    assertEquals(219.8f, sale.totalPrice());
  }

}
