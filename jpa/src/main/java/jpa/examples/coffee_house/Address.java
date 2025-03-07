package jpa.examples.coffee_house;

import jakarta.persistence.Embeddable;

@Embeddable
record Address(
    String street,
    String city,
    String state,
    String zip) {
}
