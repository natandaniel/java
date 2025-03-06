package jpa.examples.online_learning_platform.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CONTENT_TYPE", discriminatorType = DiscriminatorType.STRING)
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public abstract class LessonContent extends BaseEntity {
  @ManyToOne
  private LessonSection lessonSection;

  protected LessonContent() {}
}
