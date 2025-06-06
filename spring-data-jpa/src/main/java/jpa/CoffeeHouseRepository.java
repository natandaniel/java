package jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
interface CoffeeHouseRepository extends JpaRepository<CoffeeHouse, Integer> {

  CoffeeHouse findByName(String name);

}