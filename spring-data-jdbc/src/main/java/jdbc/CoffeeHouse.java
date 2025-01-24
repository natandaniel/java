package jdbc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("coffee_houses")
@RequiredArgsConstructor
class CoffeeHouse {
  @Id
  private final short id;
  @Getter
  private final String name;
}
