package jpa.examples.coffee_house;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

class CoffeeHouseRepository {

  private final EntityManager entityManager;

  public CoffeeHouseRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public CoffeeHouse findByName(String name) {
    TypedQuery<CoffeeHouse> query =
        entityManager.createQuery("SELECT ch FROM coffee_house ch WHERE ch.name = :name",
            CoffeeHouse.class);
    query.setParameter("name", name);
    return query.getResultStream().findFirst().orElse(null);
  }

  public CoffeeHouse save(CoffeeHouse coffeeHouse) {
    if (coffeeHouse.getId() == null) entityManager.persist(coffeeHouse);
    else coffeeHouse = entityManager.merge(coffeeHouse);

    return coffeeHouse;
  }

  public void delete(CoffeeHouse coffeeHouse) {
    CoffeeHouse managedCoffeeHouse =
        entityManager.contains(coffeeHouse) ? coffeeHouse : entityManager.merge(coffeeHouse);
    entityManager.remove(managedCoffeeHouse);
  }
}