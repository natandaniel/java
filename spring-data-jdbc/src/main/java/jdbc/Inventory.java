package jdbc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
class Inventory {
  private final short productId;
  private final int stockLevel;
  private final LocalDateTime lastUpdated;
}
