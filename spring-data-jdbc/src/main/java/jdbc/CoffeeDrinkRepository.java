package jdbc;

import org.springframework.data.repository.CrudRepository;

interface CoffeeDrinkRepository extends CrudRepository<CoffeeDrink, String> {
}