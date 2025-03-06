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

@Entity(name = "lessons")
@Data
@EqualsAndHashCode(callSuper = true)
public class Lesson extends BaseEntity {
  @NotBlank
  @Size(max = 255)
  private String title;
  @NotBlank
  @Size(max = 2000)
  private String description;
  @Pattern(regexp = "^https?://.*", message = "Invalid URL")
  private String imageUrl;
  @ManyToOne
  private CourseModule module;
  @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<LessonSection> sections = new ArrayList<>();

  protected Lesson() {}

  public Lesson(Integer id, int order, String title, String description, String imageUrl) {
    super(id, order);
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
  }

  void addSection(LessonSection section) {
    sections.add(section);
  }

  void removeSection(LessonSection section) {
    sections.remove(section);
  }
}
