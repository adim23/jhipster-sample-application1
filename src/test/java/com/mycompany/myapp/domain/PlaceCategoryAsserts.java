package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaceCategoryAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlaceCategoryAllPropertiesEquals(PlaceCategory expected, PlaceCategory actual) {
        assertPlaceCategoryAutoGeneratedPropertiesEquals(expected, actual);
        assertPlaceCategoryAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlaceCategoryAllUpdatablePropertiesEquals(PlaceCategory expected, PlaceCategory actual) {
        assertPlaceCategoryUpdatableFieldsEquals(expected, actual);
        assertPlaceCategoryUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlaceCategoryAutoGeneratedPropertiesEquals(PlaceCategory expected, PlaceCategory actual) {
        assertThat(expected)
            .as("Verify PlaceCategory auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlaceCategoryUpdatableFieldsEquals(PlaceCategory expected, PlaceCategory actual) {
        assertThat(expected)
            .as("Verify PlaceCategory relevant properties")
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getEnabled()).as("check enabled").isEqualTo(actual.getEnabled()))
            .satisfies(e -> assertThat(e.getIcon()).as("check icon").isEqualTo(actual.getIcon()))
            .satisfies(e -> assertThat(e.getCreatedDate()).as("check createdDate").isEqualTo(actual.getCreatedDate()))
            .satisfies(e -> assertThat(e.getDefaultImage()).as("check defaultImage").isEqualTo(actual.getDefaultImage()))
            .satisfies(e -> assertThat(e.getDefaultImageData()).as("check defaultImageData").isEqualTo(actual.getDefaultImageData()))
            .satisfies(
                e ->
                    assertThat(e.getDefaultImageDataContentType())
                        .as("check defaultImageData contenty type")
                        .isEqualTo(actual.getDefaultImageDataContentType())
            );
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertPlaceCategoryUpdatableRelationshipsEquals(PlaceCategory expected, PlaceCategory actual) {
        assertThat(expected)
            .as("Verify PlaceCategory relationships")
            .satisfies(e -> assertThat(e.getPlaces()).as("check places").isEqualTo(actual.getPlaces()));
    }
}