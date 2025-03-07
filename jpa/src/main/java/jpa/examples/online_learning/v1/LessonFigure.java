package jpa.examples.online_learning.v1;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "lesson_figures")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class LessonFigure extends LessonContent {
  private String alt;
  private String title;
  private String url;

  protected LessonFigure() {}

  protected LessonFigure(Integer id, int order, LessonSection lessonSection, String alt,
      String title, String url) {
    super(id, order, lessonSection);
    this.alt = alt;
    this.title = title;
    this.url = url;
  }

}
