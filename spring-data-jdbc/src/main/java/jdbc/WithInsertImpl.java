package jdbc;

import org.springframework.data.jdbc.core.JdbcAggregateTemplate;

public class WithInsertImpl<T> implements WithInsert<T> {
  private final JdbcAggregateTemplate template;

  public WithInsertImpl(JdbcAggregateTemplate template) {
    this.template = template;
  }

  @Override
  public <E extends T> void insert(E entity) {
    template.insert(entity);
  }

  @Override
  public <E extends T> void insertAll(Iterable<E> entities) {
    template.insertAll(entities);
  }
}