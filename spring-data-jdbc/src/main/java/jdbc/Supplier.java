package jdbc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("suppliers")
@AllArgsConstructor
@Getter
public class Supplier {
  @Id
  private final int id;
  private final String name;
}