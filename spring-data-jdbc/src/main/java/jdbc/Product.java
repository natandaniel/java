package jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("product")
@AllArgsConstructor
@Getter
abstract class Product {
  @Id
  private final short id;
  private final String name;
  private final float price;
}