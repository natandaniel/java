package coffees;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

  public static void main(String[] args) {
    AnnotationConfigApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfiguration.class);
    context.registerShutdownHook();

    MysqlJdbcTemplateCarsRepository repository =
        context.getBean(MysqlJdbcTemplateCarsRepository.class);

  }
}
