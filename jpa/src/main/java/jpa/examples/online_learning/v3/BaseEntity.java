package jpa.examples.online_learning.v3;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;

@MappedSuperclass
@Data
@AllArgsConstructor
public abstract class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Integer id;
  private int index;

  protected BaseEntity() {}

}
