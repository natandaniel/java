package jpa;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "supplier")
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(force = true)
class Supplier {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private final Integer id;
  @With(AccessLevel.PRIVATE)
  private final String name;
}