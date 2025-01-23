package jdbc;

import org.springframework.data.repository.CrudRepository;

public interface SalesRepository extends CrudRepository<Sales, Short> {
}