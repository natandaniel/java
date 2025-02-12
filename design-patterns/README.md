# Design patterns

This module contains example code of design patterns as defined in the book *Design Patterns: Elements of reusable
object-oriented software*
by authors Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides.

The purpose of this readme file is to compile my personal notes of the information provided in this book.

## Creational patterns

<ul>
<li>abstract the instantiation process</li>
<li>improve design flexibility by removing from an object the responsibility of creating its dependencies</li>
</ul>

### Abstract Factory

Provides an interface for creating families of related or dependent objects without specifying their concrete classes.

To be used:
<ul>
<li>when object creation independency is required</li>
<li>for configuring one object belonging to a family of related and possible candidate objects</li>
<li>for enforcing the cohesion of a family of objects</li>
<li>for providing a class library of objects revealing only their interfaces</li>
</ul>

Participants:
<ul>
<li>AbstractFactory (declares factory methods)</li>
<li>ConcreteFactory (usually a singleton; can be a prototype to avoid having concrete factories differing only slightly)</li>
<li>AbstractProduct</li>
<li>ConcreteProduct</li>
<li>Client</li>
</ul>

### Builder

Separate the construction of a complex object from its representation so that the same construction process can create
different representations.

Participants:
<ul>
<li>Builder: specifies the construction process of a Product</li>
<li>ConcreteBuilder: implements Builder</li>
<li>Product</li>
<li>Director: uses the Builder to create a Product</li>
<li>Client: configures the Director with a Builder</li>
</ul>

### Factory Method

Define an interface for creating an object, but let subclasses decide which class to instantiate.

To be used:
<ul>
<li>when a class can't anticipate the class of objects it must create</li>
<li>when a class wants its subclasses to specify the objects it creates</li>
<li>when classes delegate responsibility to one of several helper classesn and you want to localize rhe knowledge of which helper subclass is the delegate</li>
</ul>

Participants:
<ul>
<li>Product</li>
<li>ConcreteProduct</li>
<li>Creator</li>
<li>ConcreteCreator</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
</ul>

### Prototype

Specify the kinds of objects to create using a prototypical instance, and create new objects by copying this prototype.

To be used when object creation independency is required and:
<ul>
<li>when the classes to instantiate are specified at run-time or </li>
<li>to avoid building a class hierarchy of factories that parallels the class hierarchy of products or</li>
<li>when instances of a class can have one of only a few different combinations of state</li>
</ul>

Participants:
<ul>
<li>Prototype: declares an interface for cloning itself</li>
<li>ConcretePrototype</li>
<li>Client: asks a prototype to clone itself</li>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
</ul>

### Singleton

Ensure a class only has one instance, and provide a global point of access to it.

To be used when:
<ul>
<li>there must be exactly one instance of a class, and it must be accessible to clients from a well-known access point</li>
<li>the sole instance should be extensible by subclassing, and clients should be able to use and extended instance without modifying their code</li>
</ul>

Participants:
<ul>
<li>Singleton</li> 
</ul>

## Structural

### Adapter

Convert the interface of a class into another interface clients expect. Adapter lets classes work together that couldn't
otherwise because of incompatible interfaces. Also known as wrapper. Enables existing code reuse.

Participants:
<ul>
<li>Target: the target interface</li> 
<li>Adaptee: the class to adapt</li>
<li>Adapter: adapts the interface of Adaptee to that of Target</li>
<li>Client: collaborates with objects conforming to the Target interface</li>
</ul>

### Bridge

Decouple an abstraction from its implementation so that the two can vary independently.

Participants:
<ul>
<li>Abstraction: an interface, owns an Implementor to which it forwards client requests</li> 
<li>RefinedAbstraction: an extension of Abstraction</li>
<li>Implementor: an interface for implementation classes, can be different from Abstraction's interface</li>
<li>ConcreteImplementor: implements Implementor</li>
</ul>

<ul>
<li>+ decouples interface and implementation</li>
<li>+ improved extensibility</li>
<li>+ hiding implementation details from clients</li>
</ul>

### Composite

Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients trat individual objects
and compositions of objects uniformly.

### Decorator

Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing
for extending functionality. Open/Close principle.

Participants:
<ul>
<li>Component: defines the interface for objects that can have responsibilities added to them dynamically</li> 
<li>ConcreteComponent</li>
<li>Decorator: maintains a reference to a Component; defines an interface that conforms to Component</li>
<li>ConcreteDecorator: adds responsibilities to the component</li>
</ul>

### Facade

Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes
the subsystem easier to use.

Participants:
<ul>
<li>Facade: knows which subsystem classes are responsible for a request; delegates client request to appropriate subsystem objects</li> 
<li>Subsystem classes: implement subsystem functionality; handle work assigned by the facade object, have no knowledge of the facade</li>
</ul>

### Flyweight

### Proxy

Provide a surrogate or placeholder for another object to control access to it.

## Behavioral

