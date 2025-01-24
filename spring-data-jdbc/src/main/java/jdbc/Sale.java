package jdbc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
class Sale {
  private final short productId;
  private final int quantity;
  private final float totalPrice;
  private final LocalDateTime saleDate;
}
