package jdbc;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

record Sale(
    @Id int id,
    String productName,
    int quantity,
    float totalPrice,
    LocalDateTime saleDate) {
}
