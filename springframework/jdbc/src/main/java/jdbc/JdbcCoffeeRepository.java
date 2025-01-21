package jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcCoffeeRepository {
  private final SimpleJdbcInsert simpleJdbcInsert;

  public JdbcCoffeeRepository(MysqlDataSource mysqlDataSource) {
    simpleJdbcInsert = new SimpleJdbcInsert(mysqlDataSource).withTableName("COFFEES");
  }

  public void insert(Coffee coffee) {
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("cof_name", coffee.getName());
    parameters.put("sup_id", coffee.getSupId());
    parameters.put("price", coffee.getPrice());
    parameters.put("sales", coffee.getSales());
    parameters.put("total", coffee.getTotal());
    simpleJdbcInsert.execute(parameters);
  }

}
