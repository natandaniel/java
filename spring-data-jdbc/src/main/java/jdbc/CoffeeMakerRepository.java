package jdbc;

import org.springframework.data.repository.CrudRepository;

interface CoffeeMakerRepository extends CrudRepository<CoffeeMaker, Short> {
}