package jdbc;

import org.springframework.data.repository.CrudRepository;

interface CoffeeMakerInventoryRepository extends CrudRepository<CoffeeMakerInventory, String> {
}