package jpa.examples.online_learning_platform.course;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "lesson_code_samples")
@DiscriminatorValue("CODE")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class LessonCode extends LessonContent {
  private String language;
  private String code;

  protected LessonCode() {}

  protected LessonCode(Integer id, int order, LessonSection lessonSection, String language,
      String code) {
    super(id, order, lessonSection);
    this.language = language;
    this.code = code;
  }
}
