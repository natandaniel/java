package jpa.examples.coffee_house;

import jakarta.persistence.Embeddable;

@Embeddable
record Product(
    String name,
    float price,
    String description,
    Float weight) {
}