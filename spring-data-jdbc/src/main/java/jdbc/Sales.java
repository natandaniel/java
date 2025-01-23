package jdbc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("sales")
@RequiredArgsConstructor
@Getter
class Sales {
  @Id
  private final short id;
  private final short productId;
  private final int quantity;
  private final float totalPrice;
  private final LocalDateTime saleDate;
}
