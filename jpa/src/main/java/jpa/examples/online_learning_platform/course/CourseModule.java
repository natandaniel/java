package jpa.examples.online_learning_platform.course;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
  @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Lesson> lessons;

  protected CourseModule() {}

}
