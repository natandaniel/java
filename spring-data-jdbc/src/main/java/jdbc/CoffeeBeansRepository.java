package jdbc;

import org.springframework.data.repository.ListCrudRepository;

interface CoffeeBeansRepository extends ListCrudRepository<CoffeeBeans, Short> {

  //List<CoffeeBeans> findByCoffeeBeanType(CoffeeBeanType coffeeBeanType);

}