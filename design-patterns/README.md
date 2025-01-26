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

## Structural

## Beahvioral

## Design pattern goals

<ul>
<li>system flexibility</li>
</ul>