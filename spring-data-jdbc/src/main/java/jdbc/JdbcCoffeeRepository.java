package jdbc;

import org.springframework.data.repository.ListCrudRepository;

public interface JdbcCoffeeRepository extends ListCrudRepository<Coffee, String> {

  int countBySupId(int id);

  CoffeeNameAndSupId getCoffeeNameAndSupIdByName(String name);

}
