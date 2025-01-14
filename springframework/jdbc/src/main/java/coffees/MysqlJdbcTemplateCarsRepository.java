package coffees;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MysqlJdbcTemplateCarsRepository {
  private final JdbcTemplate mysqlJdbcTemplate;

  public MysqlJdbcTemplateCarsRepository(
      @Qualifier("mysql") JdbcTemplate mysqlJdbcTemplate) {
    this.mysqlJdbcTemplate = mysqlJdbcTemplate;
  }

}
