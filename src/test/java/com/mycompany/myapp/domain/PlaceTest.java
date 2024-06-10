package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.DestinationTestSamples.*;
import static com.mycompany.myapp.domain.ImageFileTestSamples.*;
import static com.mycompany.myapp.domain.PlaceCategoryTestSamples.*;
import static com.mycompany.myapp.domain.PlaceTestSamples.*;
import static com.mycompany.myapp.domain.TagTestSamples.*;
import static com.mycompany.myapp.domain.TourStepTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PlaceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Place.class);
        Place place1 = getPlaceSample1();
        Place place2 = new Place();
        assertThat(place1).isNotEqualTo(place2);

        place2.setId(place1.getId());
        assertThat(place1).isEqualTo(place2);

        place2 = getPlaceSample2();
        assertThat(place1).isNotEqualTo(place2);
    }

    @Test
    void tourStepTest() {
        Place place = getPlaceRandomSampleGenerator();
        TourStep tourStepBack = getTourStepRandomSampleGenerator();

        place.addTourStep(tourStepBack);
        assertThat(place.getTourSteps()).containsOnly(tourStepBack);
        assertThat(tourStepBack.getPlace()).isEqualTo(place);

        place.removeTourStep(tourStepBack);
        assertThat(place.getTourSteps()).doesNotContain(tourStepBack);
        assertThat(tourStepBack.getPlace()).isNull();

        place.tourSteps(new HashSet<>(Set.of(tourStepBack)));
        assertThat(place.getTourSteps()).containsOnly(tourStepBack);
        assertThat(tourStepBack.getPlace()).isEqualTo(place);

        place.setTourSteps(new HashSet<>());
        assertThat(place.getTourSteps()).doesNotContain(tourStepBack);
        assertThat(tourStepBack.getPlace()).isNull();
    }

    @Test
    void imagesTest() {
        Place place = getPlaceRandomSampleGenerator();
        ImageFile imageFileBack = getImageFileRandomSampleGenerator();

        place.addImages(imageFileBack);
        assertThat(place.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getPlace()).isEqualTo(place);

        place.removeImages(imageFileBack);
        assertThat(place.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getPlace()).isNull();

        place.images(new HashSet<>(Set.of(imageFileBack)));
        assertThat(place.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getPlace()).isEqualTo(place);

        place.setImages(new HashSet<>());
        assertThat(place.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getPlace()).isNull();
    }

    @Test
    void contentsTest() {
        Place place = getPlaceRandomSampleGenerator();
        Content contentBack = getContentRandomSampleGenerator();

        place.addContents(contentBack);
        assertThat(place.getContents()).containsOnly(contentBack);
        assertThat(contentBack.getPlace()).isEqualTo(place);

        place.removeContents(contentBack);
        assertThat(place.getContents()).doesNotContain(contentBack);
        assertThat(contentBack.getPlace()).isNull();

        place.contents(new HashSet<>(Set.of(contentBack)));
        assertThat(place.getContents()).containsOnly(contentBack);
        assertThat(contentBack.getPlace()).isEqualTo(place);

        place.setContents(new HashSet<>());
        assertThat(place.getContents()).doesNotContain(contentBack);
        assertThat(contentBack.getPlace()).isNull();
    }

    @Test
    void tagsTest() {
        Place place = getPlaceRandomSampleGenerator();
        Tag tagBack = getTagRandomSampleGenerator();

        place.addTags(tagBack);
        assertThat(place.getTags()).containsOnly(tagBack);

        place.removeTags(tagBack);
        assertThat(place.getTags()).doesNotContain(tagBack);

        place.tags(new HashSet<>(Set.of(tagBack)));
        assertThat(place.getTags()).containsOnly(tagBack);

        place.setTags(new HashSet<>());
        assertThat(place.getTags()).doesNotContain(tagBack);
    }

    @Test
    void categoryTest() {
        Place place = getPlaceRandomSampleGenerator();
        PlaceCategory placeCategoryBack = getPlaceCategoryRandomSampleGenerator();

        place.addCategory(placeCategoryBack);
        assertThat(place.getCategories()).containsOnly(placeCategoryBack);

        place.removeCategory(placeCategoryBack);
        assertThat(place.getCategories()).doesNotContain(placeCategoryBack);

        place.categories(new HashSet<>(Set.of(placeCategoryBack)));
        assertThat(place.getCategories()).containsOnly(placeCategoryBack);

        place.setCategories(new HashSet<>());
        assertThat(place.getCategories()).doesNotContain(placeCategoryBack);
    }

    @Test
    void destinationTest() {
        Place place = getPlaceRandomSampleGenerator();
        Destination destinationBack = getDestinationRandomSampleGenerator();

        place.setDestination(destinationBack);
        assertThat(place.getDestination()).isEqualTo(destinationBack);

        place.destination(null);
        assertThat(place.getDestination()).isNull();
    }
}
