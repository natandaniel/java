package jdbc;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;

import java.time.LocalDateTime;

record CoffeeMaker(
    @Id int id,
    @Embedded.Empty Product product,
    CoffeeMakerType coffeeMakerType,
    int stockLevel,
    LocalDateTime lastUpdated) {
}