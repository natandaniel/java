package jdbc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { JdbcConfiguration.class })
@Sql({ "/create-tables.sql", "/populate-tables.sql" })
@Sql(scripts = "/drop-tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SupplierRepositoryTests {
  @Autowired
  private SupplierRepository supplierRepository;

  @Test
  void testFindingAllSuppliers() {
    List<Supplier> suppliers = supplierRepository.findAll();
    assertEquals(10, suppliers.size());
  }
}
