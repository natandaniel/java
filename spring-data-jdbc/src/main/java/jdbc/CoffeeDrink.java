package jdbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

record CoffeeDrink(@Id int id, @Embedded.Empty Product product) {
}