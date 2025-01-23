package jdbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("supplier")
public record CoffeeSupplier(
    @Id int id, String name, String street, String city, String state, String zip) {
}
