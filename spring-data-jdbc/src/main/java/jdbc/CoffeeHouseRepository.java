package jdbc;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
interface CoffeeHouseRepository extends ListCrudRepository<CoffeeHouse, Integer> {

  CoffeeHouse findByName(String name);

}