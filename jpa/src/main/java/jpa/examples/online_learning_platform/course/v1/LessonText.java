package jpa.examples.online_learning_platform.course.v1;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "lesson_texts")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class LessonText extends LessonContent {
  private String text;

  protected LessonText() {}

  protected LessonText(Integer id, int order, LessonSection lessonSection, String text) {
    super(id, order, lessonSection);
    this.text = text;
  }

}
