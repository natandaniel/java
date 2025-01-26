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

### Factory Method

### Builder

### Prototype

## Structural

## Beahvioral

## Design pattern goals

<ul>
<li>system flexibility</li>
</ul>