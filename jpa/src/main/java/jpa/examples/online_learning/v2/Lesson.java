package jpa.examples.online_learning.v2;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
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
  @Lob
  private String content;

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
