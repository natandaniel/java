package jpa.examples.online_learning_platform.course;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("CODE")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class LessonCode extends LessonContent {
  private String language;
  private String code;

  protected LessonCode() {}
}
