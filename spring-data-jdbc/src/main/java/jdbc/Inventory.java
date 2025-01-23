package jdbc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("inventory")
@RequiredArgsConstructor
@Getter
class Inventory {
  @Id
  private final short id;
  private final short productId;
  private final int stockLevel;
  private final LocalDateTime lastUpdated;
}
