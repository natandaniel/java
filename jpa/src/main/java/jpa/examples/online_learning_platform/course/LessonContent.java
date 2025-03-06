package jpa.examples.online_learning_platform.course;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class LessonContent {
  @Id
  @GeneratedValue(strategy = GenerationType.TABLE)
  protected Integer id;
  private int index;
  @ManyToOne
  private LessonSection lessonSection;

  protected LessonContent() {}

  protected LessonContent(Integer id, int index, LessonSection lessonSection) {
    this.id = id;
    this.index = index;
    this.lessonSection = lessonSection;
  }
}
