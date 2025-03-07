package jpa.examples.online_learning_platform.course.v3;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "courses")
@Data
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseEntity {
  @NotBlank
  @Size(max = 255)
  private String title;
  @NotBlank
  @Size(max = 10000)
  private String description;
  @Pattern(regexp = "^https?://.*", message = "Invalid URL")
  private String imageUrl;
  private float price;
  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch =
      FetchType.EAGER)
  private List<CourseModule> modules = new ArrayList<>();

  protected Course() {
    super();
  }

  void addModule(CourseModule module) {
    modules.add(module);
  }

  void removeModule(CourseModule module) {
    modules.remove(module);
  }

}
