package jdbc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Table;

@RequiredArgsConstructor
@Getter
@Table("coffee_maker_inventory")
public class CoffeeMakerInventory {
  @Id
  private final short id;
  @Embedded.Empty
  private final Inventory inventory;
}
