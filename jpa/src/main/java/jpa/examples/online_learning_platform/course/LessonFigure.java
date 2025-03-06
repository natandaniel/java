package jpa.examples.online_learning_platform.course;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@DiscriminatorValue("FIGURE")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class LessonFigure extends LessonContent {
  private String alt;
  private String title;
  private String url;

  protected LessonFigure() {}

}
