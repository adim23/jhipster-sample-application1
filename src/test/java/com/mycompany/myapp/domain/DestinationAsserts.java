package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DestinationAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDestinationAllPropertiesEquals(Destination expected, Destination actual) {
        assertDestinationAutoGeneratedPropertiesEquals(expected, actual);
        assertDestinationAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDestinationAllUpdatablePropertiesEquals(Destination expected, Destination actual) {
        assertDestinationUpdatableFieldsEquals(expected, actual);
        assertDestinationUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDestinationAutoGeneratedPropertiesEquals(Destination expected, Destination actual) {
        assertThat(expected)
            .as("Verify Destination auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDestinationUpdatableFieldsEquals(Destination expected, Destination actual) {
        assertThat(expected)
            .as("Verify Destination relevant properties")
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getEnabled()).as("check enabled").isEqualTo(actual.getEnabled()))
            .satisfies(e -> assertThat(e.getDefaultImage()).as("check defaultImage").isEqualTo(actual.getDefaultImage()))
            .satisfies(e -> assertThat(e.getDefaultImageData()).as("check defaultImageData").isEqualTo(actual.getDefaultImageData()))
            .satisfies(
                e ->
                    assertThat(e.getDefaultImageDataContentType())
                        .as("check defaultImageData contenty type")
                        .isEqualTo(actual.getDefaultImageDataContentType())
            )
            .satisfies(e -> assertThat(e.getCssStyle()).as("check cssStyle").isEqualTo(actual.getCssStyle()))
            .satisfies(e -> assertThat(e.getCreatedDate()).as("check createdDate").isEqualTo(actual.getCreatedDate()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDestinationUpdatableRelationshipsEquals(Destination expected, Destination actual) {}
}
