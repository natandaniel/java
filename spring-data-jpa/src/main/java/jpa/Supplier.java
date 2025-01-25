package jpa;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "supplier")
@Entity
@Getter
@NoArgsConstructor(force = true)
class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Integer id;
  private final String name;
}