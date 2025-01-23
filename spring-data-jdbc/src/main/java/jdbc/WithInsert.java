package jdbc;

public interface WithInsert<T> {
  <E extends T> void insert(E entity);

  <E extends T> void insertAll(Iterable<E> entities);
}