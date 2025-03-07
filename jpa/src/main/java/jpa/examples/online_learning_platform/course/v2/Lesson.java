package jpa.examples.online_learning_platform.course.v2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
  @ManyToOne
  private CourseModule module;
  @Column(columnDefinition = "jsonb")
  private String content; // Flexible JSON for sections and contents

  protected Lesson() {}

  public Lesson(Integer id, int order, String title, String description, CourseModule module,
      String content) {
    super(id, order);
    this.title = title;
    this.description = description;
    this.module = module;
    this.content = content;
  }

}
