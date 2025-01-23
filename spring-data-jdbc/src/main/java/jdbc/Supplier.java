package jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("supplier")
@AllArgsConstructor
@Getter
public class Supplier {
  @Id
  private final short id;
  private final String name;
}