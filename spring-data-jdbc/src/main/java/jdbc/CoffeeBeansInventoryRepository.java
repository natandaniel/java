package jdbc;

import org.springframework.data.repository.CrudRepository;

interface CoffeeBeansInventoryRepository extends CrudRepository<CoffeeBeansInventory, Short> {
}