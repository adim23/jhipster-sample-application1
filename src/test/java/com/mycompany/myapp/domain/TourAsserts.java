package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class TourAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTourAllPropertiesEquals(Tour expected, Tour actual) {
        assertTourAutoGeneratedPropertiesEquals(expected, actual);
        assertTourAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTourAllUpdatablePropertiesEquals(Tour expected, Tour actual) {
        assertTourUpdatableFieldsEquals(expected, actual);
        assertTourUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTourAutoGeneratedPropertiesEquals(Tour expected, Tour actual) {
        assertThat(expected)
            .as("Verify Tour auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTourUpdatableFieldsEquals(Tour expected, Tour actual) {
        assertThat(expected)
            .as("Verify Tour relevant properties")
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getEnabled()).as("check enabled").isEqualTo(actual.getEnabled()))
            .satisfies(e -> assertThat(e.getKind()).as("check kind").isEqualTo(actual.getKind()))
            .satisfies(e -> assertThat(e.getMode()).as("check mode").isEqualTo(actual.getMode()))
            .satisfies(e -> assertThat(e.getIcon()).as("check icon").isEqualTo(actual.getIcon()))
            .satisfies(e -> assertThat(e.getDuration()).as("check duration").isEqualTo(actual.getDuration()))
            .satisfies(e -> assertThat(e.getDurationMeasure()).as("check durationMeasure").isEqualTo(actual.getDurationMeasure()))
            .satisfies(e -> assertThat(e.getPetFriendly()).as("check petFriendly").isEqualTo(actual.getPetFriendly()))
            .satisfies(e -> assertThat(e.getKidsAllowed()).as("check kidsAllowed").isEqualTo(actual.getKidsAllowed()))
            .satisfies(e -> assertThat(e.getSmoking()).as("check smoking").isEqualTo(actual.getSmoking()))
            .satisfies(e -> assertThat(e.getAvailableFromDate()).as("check availableFromDate").isEqualTo(actual.getAvailableFromDate()))
            .satisfies(e -> assertThat(e.getAvailableToDate()).as("check availableToDate").isEqualTo(actual.getAvailableToDate()))
            .satisfies(e -> assertThat(e.getInitialPrice()).as("check initialPrice").isEqualTo(actual.getInitialPrice()))
            .satisfies(e -> assertThat(e.getPrice()).as("check price").isEqualTo(actual.getPrice()))
            .satisfies(e -> assertThat(e.getBadge()).as("check badge").isEqualTo(actual.getBadge()))
            .satisfies(e -> assertThat(e.getRating()).as("check rating").isEqualTo(actual.getRating()))
            .satisfies(e -> assertThat(e.getWidgetId()).as("check widgetId").isEqualTo(actual.getWidgetId()))
            .satisfies(e -> assertThat(e.getExternalId()).as("check externalId").isEqualTo(actual.getExternalId()))
            .satisfies(e -> assertThat(e.getCreatedDate()).as("check createdDate").isEqualTo(actual.getCreatedDate()))
            .satisfies(e -> assertThat(e.getDefaultImage()).as("check defaultImage").isEqualTo(actual.getDefaultImage()))
            .satisfies(e -> assertThat(e.getDefaultImageData()).as("check defaultImageData").isEqualTo(actual.getDefaultImageData()))
            .satisfies(
                e ->
                    assertThat(e.getDefaultImageDataContentType())
                        .as("check defaultImageData contenty type")
                        .isEqualTo(actual.getDefaultImageDataContentType())
            )
            .satisfies(e -> assertThat(e.getAccessibility()).as("check accessibility").isEqualTo(actual.getAccessibility()))
            .satisfies(e -> assertThat(e.getAudioGuide()).as("check audioGuide").isEqualTo(actual.getAudioGuide()))
            .satisfies(e -> assertThat(e.getTourGuide()).as("check tourGuide").isEqualTo(actual.getTourGuide()))
            .satisfies(e -> assertThat(e.getCssStyle()).as("check cssStyle").isEqualTo(actual.getCssStyle()))
            .satisfies(e -> assertThat(e.getDeparture()).as("check departure").isEqualTo(actual.getDeparture()))
            .satisfies(e -> assertThat(e.getReturnTime()).as("check returnTime").isEqualTo(actual.getReturnTime()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertTourUpdatableRelationshipsEquals(Tour expected, Tour actual) {
        assertThat(expected)
            .as("Verify Tour relationships")
            .satisfies(e -> assertThat(e.getContent()).as("check content").isEqualTo(actual.getContent()))
            .satisfies(e -> assertThat(e.getMeetingPoint()).as("check meetingPoint").isEqualTo(actual.getMeetingPoint()))
            .satisfies(e -> assertThat(e.getFinishPoint()).as("check finishPoint").isEqualTo(actual.getFinishPoint()))
            .satisfies(e -> assertThat(e.getTourExtras()).as("check tourExtras").isEqualTo(actual.getTourExtras()))
            .satisfies(e -> assertThat(e.getTags()).as("check tags").isEqualTo(actual.getTags()))
            .satisfies(e -> assertThat(e.getPromotions()).as("check promotions").isEqualTo(actual.getPromotions()))
            .satisfies(e -> assertThat(e.getCategories()).as("check categories").isEqualTo(actual.getCategories()))
            .satisfies(e -> assertThat(e.getDestination()).as("check destination").isEqualTo(actual.getDestination()))
            .satisfies(e -> assertThat(e.getDefaultCategory()).as("check defaultCategory").isEqualTo(actual.getDefaultCategory()));
    }
}