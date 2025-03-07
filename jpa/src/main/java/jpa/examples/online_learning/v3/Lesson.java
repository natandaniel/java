package jpa.examples.online_learning.v3;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "lessons")
@Data
@EqualsAndHashCode(callSuper = true)
public class Lesson extends BaseEntity {
  @NotBlank
  @Size(max = 255)
  private String title; // Lesson title

  @NotBlank
  @Size(max = 2000)
  private String description; // Brief description about the lesson

  @ManyToOne
  private CourseModule module; // Relationship to the course module that owns this lesson

  @Pattern(regexp = "^https?://.*", message = "Content URL must be a valid URL")
  private String contentUrl; // URL to the hosted lesson content

  protected Lesson() {}

  public Lesson(Integer id, int order, String title, String description, CourseModule module,
      String contentUrl) {
    super(id, order);
    this.title = title;
    this.description = description;
    this.module = module;
    this.contentUrl = contentUrl;
  }
}