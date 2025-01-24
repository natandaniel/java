package jdbc;

import org.springframework.data.repository.ListCrudRepository;

interface CoffeeHouseRepository extends ListCrudRepository<CoffeeHouse, Integer> {

  CoffeeHouse findByName(String name);

}