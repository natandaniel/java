# Composite Design Pattern

---

## Intent

Compose objects into tree-like structures to represent part-whole hierarchies. This pattern allows clients to treat
individual objects and compositions of objects uniformly.

---

## Motivation

- Complex systems often consist of primitive objects and composite objects that are aggregates of these primitives (
  e.g., a folder containing files and other subfolders).
- Clients need a single, unified abstraction to interact with both primitives and composites without worrying about the
  underlying structure.
- Example: In a GUI framework, a window may contain buttons, text elements, and nested panels. The Composite pattern
  enables uniformly interacting with individual controls and groups of controls.

---

## Applicability

Use the Composite Pattern when:

1. You want to represent part-whole hierarchies of objects.
2. You want clients to treat individual objects and compositions uniformly (e.g., a file system where files and folders
   are manipulated using the same operations like `add`, `delete`, `getDetails`).

---

## Structure

### UML Diagram

```plaintext
        +--------------------+                
        |      Client        |                
        +--------------------+                
                    |                         
                    | uses                    
                    v                         
        +--------------------+                
        |   Component        | <------------+ 
        |--------------------|              |
        | + operation()      |              |
        | + add(Component)   |              |
        | + remove(Component)|              |
        | + getChildren()    |              |
        +--------------------+              |  
                    /\                      |
                implements                  |
       +--------------------+       +--------------------+  
       |       Leaf         |       |     Composite      |  
       |--------------------|       |--------------------|  
       | + operation()      |       | + operation()      |  
       |                    |       | + add(Component)   |  
       |                    |       | + remove(Component)|  
       |                    |       | + getChildren()    |  
       +--------------------+       +--------------------+                 
```

---

## Participants

1. **Component**
    - Defines the interface for objects in the structure.
    - Declares methods like `operation()`, `add(Component)`, `remove(Component)`, and `getChildren()`.

2. **Leaf**
    - Represents individual objects with no child components.
    - Implements `operation()` and provides behavior specific to the leaf.

3. **Composite**
    - Represents an aggregate of `Component` objects (both `Leaf` and other `Composite` objects).
    - Implements `add(Component)`, `remove(Component)`, and `getChildren()` to manage child components.
    - Delegates operations to child components.

4. **Client**
    - Uses the `Component` interface to interact with both `Leaf` and `Composite` objects uniformly.

---

## Collaborations

- **Client** interacts with objects through the `Component` interface.
- **Composite** delegates operations to its child components.
- **Leaf** handles operations directly without delegation, as it has no children.

---

## Benefits and Drawbacks

### Benefits

1. **Defining class hierarchies**: Simplify the definition of complex structures using primitive and composite objects.
2. **Simplifying the client**: Clients are oblivious to whether they are working with individual objects or composite
   structures.
3. **Easy to add new components**: New leaves and composites can be added with little to no impact on existing code.

### Drawbacks

1. **Design overly general**: No restrictions prevent adding specific component types at compile time (e.g., a folder
   could incorrectly contain a button).
2. **Excessive flexibility**: Misuse is possible when creating overly loose hierarchies.

---

## Implementation Considerations

1. **Explicit parent references**: Composite objects (e.g., folder) might reference a parent to easily traverse back the
   hierarchy.
2. **Sharing components**: Consider whether child components (e.g., shared subfolders) can belong to multiple
   composites.
3. **Maximizing the component interface**: Ensure the operations fit both leaves and composites.
4. **Declaring child management operations**: Implement `add`/`remove` in the `Composite` class only if they make sense.
5. **Should `Component` manage a list of children?** Component may optionally provide default implementations for child
   management.
6. **Child ordering**: If order matters (e.g., GUI layout), handle this explicitly.
7. **Caching to improve performance**: Cache results (e.g., child rendering) if operations are repetitive.
8. **Who should delete components?** Decide if parents should delete children when destroyed, or if ownership is
   external.
9. **Choosing the best data structure**: Optimize for your use case (e.g., `List`, `Set`, or `Map` for child
   components).

---

## Sample Code: Car Part-Whole Hierarchy

### Explanation:
- **All operations are defined in the `CarPart` interface** for maximum transparency.
- **Leaves do not implement child-related operations** such as `addComponent()` or `removeComponent()`. These operations throw `UnsupportedOperationException` by default.
- **Composites implement child-related operations**, handle their child list, and override `getComposite()` to return `this`.

### Code:

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Component Interface
public interface CarComponent {
    void assemble();

    default void addComponent(CarPart component) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    default void removeComponent(CarPart component) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    default List<CarPart> getChildren() {
        return Collections.emptyList(); // Return an empty list by default
    }

    default CarPart getComposite() {
        return null; // Return null by default for leaves
    }
}

// Leaf Component
public class Wheel implements CarPart {
    @Override
    public void assemble() {
        System.out.println("Assembling Wheel");
    }
}

// Another Leaf Component
public class Engine implements CarPart {
    @Override
    public void assemble() {
        System.out.println("Assembling Engine");
    }
}

// Composite Component
public class Chassis implements CarPart {
    private List<CarPart> components = new ArrayList<>();

    @Override
    public void assemble() {
        System.out.println("Assembling Chassis...");
        for (CarPart component : components) {
            component.assemble();
        }
    }

    @Override
    public void addComponent(CarPart component) {
        components.add(component);
    }

    @Override
    public void removeComponent(CarPart component) {
        components.remove(component);
    }

    @Override
    public List<CarPart> getChildren() {
        return components;
    }

    @Override
    public CarPart getComposite() {
        return this; // Return this, as it's a composite
    }
}

// Another Composite Component
public class Car implements CarPart {
    private List<CarPart> components = new ArrayList<>();

    @Override
    public void assemble() {
        System.out.println("Assembling Car...");
        for (CarPart component : components) {
            component.assemble();
        }
    }

    @Override
    public void addComponent(CarPart component) {
        components.add(component);
    }

    @Override
    public void removeComponent(CarPart component) {
        components.remove(component);
    }

    @Override
    public List<CarPart> getChildren() {
        return components;
    }

    @Override
    public CarPart getComposite() {
        return this; // Return this, as it's a composite
    }
}

// Client
public class Client {
    public static void main(String[] args) {
        CarPart wheel1 = new Wheel();
        CarPart wheel2 = new Wheel();
        CarPart engine = new Engine();

        Chassis chassis = new Chassis();
        chassis.addComponent(wheel1);
        chassis.addComponent(wheel2);

        Car car = new Car();
        car.addComponent(chassis);
        car.addComponent(engine);

        car.assemble();
    }
}
```

---

## Known Uses

1. **Graphic Editors**: Shapes can contain primitive shapes or groups of shapes (e.g., rectangles inside a layer).
2. **GUI Frameworks**: Windows, panels, buttons, and nested containers.
3. **File Systems**: Files and folders organized into a tree-like hierarchy.

---

---

## Related Patterns

1. **Chain of Responsibility**
    - The parent-child link in a Composite structure is often used to implement a Chain of Responsibility. When a child
      component cannot handle a request, it propagates the request to its parent, enabling a natural delegation
      mechanism in the hierarchy.

2. **Decorator**
    - Both Composite and Decorator share recursive composition. Decorator focuses on adding responsibilities to
      individual objects dynamically, while Composite emphasizes part-whole relationships. Decorators can also wrap
      components in a Composite structure to add behavior.

3. **Flyweight**
    - Flyweight and Composite can be combined to reduce memory usage when dealing with a large number of fine-grained
      objects representing similar data. Shared Flyweight objects are used to store intrinsic state, minimizing
      duplication in a Composite hierarchy.

4. **Iterator**
    - An Iterator is often used with Composite structures to provide a unified way to traverse all elements in a
      hierarchy, including both leaves and composites. For example, it can allow traversal through tree-like structures
      such as file systems.

5. **Visitor**
    - Visitor localizes operations and behavior in a separate class that would otherwise be distributed across
      `Composite` and `Leaf` classes. This makes it easier to define new operations on the structure without changing
      its classes, enabling operations like gathering statistics, exporting data, or applying transformations to the
      hierarchy.
