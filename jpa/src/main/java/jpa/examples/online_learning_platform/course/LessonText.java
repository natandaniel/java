package jpa.examples.online_learning_platform.course;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("TEXT")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class LessonText extends LessonContent {
  private String text;

  protected LessonText() {}

}
