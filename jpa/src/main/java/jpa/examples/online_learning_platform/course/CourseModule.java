package jpa.examples.online_learning_platform.course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "course_modules")
@Data
@EqualsAndHashCode(callSuper = true)
public class CourseModule extends BaseEntity {
  @NotBlank
  @Size(max = 255)
  private String title;
  @NotBlank
  @Size(max = 5000)
  private String description;
  @Pattern(regexp = "^https?://.*", message = "Invalid URL")
  private String imageUrl;
  @ManyToOne
  private Course course;
  @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Lesson> lessons = new ArrayList<>();

  protected CourseModule() {}

  public CourseModule(Course course, int order, String title, String description, String imageUrl) {
    super(null, order);
    this.course = course;
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  void addLesson(Lesson lesson) {
    lessons.add(lesson);
  }

  void removeLesson(Lesson lesson) {
    lessons.remove(lesson);
  }

}
