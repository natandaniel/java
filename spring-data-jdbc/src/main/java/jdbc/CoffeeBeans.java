package jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_beans")
@Getter
@NoArgsConstructor
@AllArgsConstructor
class CoffeeBeans {
  @Id
  private short id;
  @Embedded.Empty
  private Product product;
  private CoffeeBeanType coffeeBeanType;

}
