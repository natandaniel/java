package jdbc;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

interface CoffeeBeansRepository extends ListCrudRepository<CoffeeBeans, Short> {

  Optional<CoffeeBeans> findByName(String name);

}