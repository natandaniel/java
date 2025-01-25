package jpa;

import org.springframework.data.repository.ListCrudRepository;

interface SupplierRepository extends ListCrudRepository<Supplier, Integer> {
}