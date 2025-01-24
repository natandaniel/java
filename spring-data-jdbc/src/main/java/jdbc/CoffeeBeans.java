package jdbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDateTime;

record CoffeeBeans(
    @Id int id,
    @Embedded.Empty Product product,
    CoffeeBeanType coffeeBeanType,
    int stockLevel,
    LocalDateTime lastUpdated) {
}
