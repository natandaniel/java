package jdbc;

import org.springframework.data.repository.CrudRepository;

interface CoffeeBeansRepository extends CrudRepository<CoffeeBeans, String> {
}