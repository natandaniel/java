package jpa.examples.online_learning_platform.course;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
  @ElementCollection(fetch = FetchType.EAGER)
  private List<CourseModule> modules = new ArrayList<>();

  protected Course() {
    super();
  }

  public Course(Integer id, int order, String title, String description, String imageUrl,
      float price) {
    super(id, order);
    this.title = title;
    this.description = description;
    this.imageUrl = imageUrl;
    this.price = price;
  }

  void addModule(CourseModule module) {
    modules.add(module);
  }

  void removeModule(CourseModule module) {
    modules.remove(module);
  }

}
