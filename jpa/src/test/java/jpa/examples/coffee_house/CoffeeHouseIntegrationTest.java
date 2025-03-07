package jpa.examples.coffee_house;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.*;

class CoffeeHouseIntegrationTest {
  private static EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;
  private CoffeeHouseRepository coffeeHouseRepository;

  @BeforeAll
  static void setupEntityManagerFactory() {
    entityManagerFactory =
        Persistence.createEntityManagerFactory("test-persistence-unit-coffee-house");
  }

  @BeforeEach
  void setupEntityManager() {
    entityManager = entityManagerFactory.createEntityManager();
    coffeeHouseRepository = new CoffeeHouseRepository(entityManager);
    executeSqlScript("populate-tables.sql");
  }

  @AfterEach
  void tearDownEntityManager() {
    executeSqlScript("clear-tables.sql");

    if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
    entityManager.close();
  }

  @AfterAll
  static void closeEntityManagerFactory() {
    if (entityManagerFactory != null) entityManagerFactory.close();
  }

  @Test
  void testFindingCoffeeHouseByName() {
    entityManager.getTransaction().begin();

    CoffeeHouse coffeeHouse = coffeeHouseRepository.findByName("Relaxing Coffee");

    entityManager.getTransaction().commit();

    assertNotNull(coffeeHouse);
    assertEquals("Relaxing Coffee", coffeeHouse.getName());
  }

  @Test
  void testCreatingACoffeeHouse() {
    entityManager.getTransaction().begin();

    CoffeeHouse coffeeHouse = coffeeHouseRepository.save(
        new CoffeeHouse("TestCoffeeHouse", new Address("street", "city", "state", "zip"), 1));

    entityManager.getTransaction().commit();

    assertNotNull(coffeeHouse);
    assertNotNull(coffeeHouse.getId());
    assertEquals("TestCoffeeHouse", coffeeHouse.getName());
  }

  @Test
  void testAddingANewCoffeeBeansProductToCoffeeHouseNamedRelaxingCoffee() {
    entityManager.getTransaction().begin();

    CoffeeHouse coffeeHouse = coffeeHouseRepository.findByName("Relaxing Coffee");

    coffeeHouse.addCoffeeBeans(
        new Product("Fabulous Roast", 45.99f, "a premium quality dark roast", 500.00f),
        CoffeeBeanType.ARABICA, 100);

    coffeeHouseRepository.save(coffeeHouse);

    entityManager.getTransaction().commit();

    assertTrue(coffeeHouseRepository.findByName("Relaxing Coffee")
                                    .getCoffeeBeans("Fabulous Roast")
                                    .isPresent());
  }

  @Test
  void testRestockingACoffeeBeansProductForCoffeeHouseNamedRelaxingCoffee() {
    entityManager.getTransaction().begin();

    CoffeeHouse coffeeHouse = coffeeHouseRepository.findByName("Relaxing Coffee");

    coffeeHouse.restockCoffeeBeans("Mountain Roast", 50);

    coffeeHouseRepository.save(coffeeHouse);

    entityManager.getTransaction().commit();

    CoffeeHouse relaxingCoffee = coffeeHouseRepository.findByName("Relaxing Coffee");
    CoffeeBeans coffeeBeans = relaxingCoffee.getCoffeeBeans("Mountain Roast").orElseThrow();

    assertEquals(100, coffeeBeans.getStockLevel());
  }

  @Test
  void testSellingACoffeeBeansProductFromCoffeeHouseNamedRelaxingCoffee() {
    entityManager.getTransaction().begin();

    CoffeeHouse coffeeHouse = coffeeHouseRepository.findByName("Relaxing Coffee");

    coffeeHouse.sellCoffeeBeans("Mountain Roast", 20);

    coffeeHouseRepository.save(coffeeHouse);

    entityManager.getTransaction().commit();

    CoffeeHouse relaxingCoffee = coffeeHouseRepository.findByName("Relaxing Coffee");
    CoffeeBeans coffeeBeans = relaxingCoffee.getCoffeeBeans("Mountain Roast").orElseThrow();

    // checking consistent inventory
    assertEquals(30, coffeeBeans.getStockLevel());

    // checking consistent sales record
    Sale sale = relaxingCoffee.getLatestSale().orElseThrow();
    assertEquals("Mountain Roast", sale.getProductName());
    assertEquals(20, sale.getQuantity());
    assertEquals(219.8f, sale.getTotalPrice(), 0.0001);
  }

  private void executeSqlScript(String scriptFileName) {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(getClass().getClassLoader().getResourceAsStream(scriptFileName)))) {

      StringBuilder sql = new StringBuilder();
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty() || line.startsWith("--")) {
          // Ignore empty lines or comments
          continue;
        }
        sql.append(line);
        if (line.endsWith(";")) { // Execute when full query is formed
          entityManager.getTransaction().begin(); // Begin transaction
          entityManager.createNativeQuery(sql.toString()).executeUpdate();
          entityManager.getTransaction().commit(); // Commit transaction
          sql.setLength(0); // Clear the builder for the next command
        }
      }
    }
    catch (Exception e) {
      throw new RuntimeException("Failed to execute SQL script: " + scriptFileName, e);
    }
  }
}