package jdbc;

import org.springframework.data.repository.CrudRepository;

interface SupplierRepository extends CrudRepository<Supplier, Integer> {
}