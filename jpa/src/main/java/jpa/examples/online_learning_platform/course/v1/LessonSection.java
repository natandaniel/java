package jpa.examples.online_learning_platform.course.v1;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity(name = "lesson_sections")
@Data
@EqualsAndHashCode(callSuper = true)
public class LessonSection extends BaseEntity {
  private String title;
  @ManyToOne
  @JoinColumn(name = "parent_id")
  private LessonSection parent;
  @ManyToOne
  private Lesson lesson;
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LessonSection> subsections = new java.util.ArrayList<>();
  @OneToMany(mappedBy = "lessonSection", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LessonContent> lessonContents = new java.util.ArrayList<>();

  protected LessonSection() {}

  public LessonSection(Integer id, String title, int order, LessonSection parent, Lesson lesson) {
    super(id, order);
    this.title = title;
    this.parent = parent;
    this.lesson = lesson;
  }

  void addSubsection(LessonSection section) {
    subsections.add(section);
  }

  void removeSubsection(LessonSection section) {
    subsections.remove(section);
  }

  void addLessonContent(LessonContent content) {
    lessonContents.add(content);
  }

  void removeLessonContent(LessonContent content) {
    lessonContents.remove(content);
  }

}
