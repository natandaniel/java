package jdbc;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;
import java.util.Arrays;

class TestUtility {

  static void executeSqlScripts(DataSource dataSource, String... sqlScripts) {
    ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
    populator.addScripts(
        Arrays.stream(sqlScripts)
              .map(ClassPathResource::new)
              .toArray(Resource[]::new));
    populator.execute(dataSource);
  }

}
