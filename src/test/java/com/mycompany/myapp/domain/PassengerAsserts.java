package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class PassengerAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPassengerAllPropertiesEquals(Passenger expected, Passenger actual) {
        assertPassengerAutoGeneratedPropertiesEquals(expected, actual);
        assertPassengerAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPassengerAllUpdatablePropertiesEquals(Passenger expected, Passenger actual) {
        assertPassengerUpdatableFieldsEquals(expected, actual);
        assertPassengerUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPassengerAutoGeneratedPropertiesEquals(Passenger expected, Passenger actual) {
        assertThat(expected)
            .as("Verify Passenger auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPassengerUpdatableFieldsEquals(Passenger expected, Passenger actual) {
        assertThat(expected)
            .as("Verify Passenger relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name").isEqualTo(actual.getName()))
            .satisfies(e -> assertThat(e.getEmail()).as("check email").isEqualTo(actual.getEmail()))
            .satisfies(e -> assertThat(e.getMobile()).as("check mobile").isEqualTo(actual.getMobile()))
            .satisfies(e -> assertThat(e.getAge()).as("check age").isEqualTo(actual.getAge()))
            .satisfies(e -> assertThat(e.getGender()).as("check gender").isEqualTo(actual.getGender()))
            .satisfies(e -> assertThat(e.getNationality()).as("check nationality").isEqualTo(actual.getNationality()))
            .satisfies(e -> assertThat(e.getCreatedDate()).as("check createdDate").isEqualTo(actual.getCreatedDate()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPassengerUpdatableRelationshipsEquals(Passenger expected, Passenger actual) {}
}
