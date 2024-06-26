package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.DestinationTestSamples.*;
import static com.mycompany.myapp.domain.ImageFileTestSamples.*;
import static com.mycompany.myapp.domain.MenuTestSamples.*;
import static com.mycompany.myapp.domain.PlaceTestSamples.*;
import static com.mycompany.myapp.domain.TourTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class DestinationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Destination.class);
        Destination destination1 = getDestinationSample1();
        Destination destination2 = new Destination();
        assertThat(destination1).isNotEqualTo(destination2);

        destination2.setId(destination1.getId());
        assertThat(destination1).isEqualTo(destination2);

        destination2 = getDestinationSample2();
        assertThat(destination1).isNotEqualTo(destination2);
    }

    @Test
    void toursTest() {
        Destination destination = getDestinationRandomSampleGenerator();
        Tour tourBack = getTourRandomSampleGenerator();

        destination.addTours(tourBack);
        assertThat(destination.getTours()).containsOnly(tourBack);
        assertThat(tourBack.getDestination()).isEqualTo(destination);

        destination.removeTours(tourBack);
        assertThat(destination.getTours()).doesNotContain(tourBack);
        assertThat(tourBack.getDestination()).isNull();

        destination.tours(new HashSet<>(Set.of(tourBack)));
        assertThat(destination.getTours()).containsOnly(tourBack);
        assertThat(tourBack.getDestination()).isEqualTo(destination);

        destination.setTours(new HashSet<>());
        assertThat(destination.getTours()).doesNotContain(tourBack);
        assertThat(tourBack.getDestination()).isNull();
    }

    @Test
    void placesTest() {
        Destination destination = getDestinationRandomSampleGenerator();
        Place placeBack = getPlaceRandomSampleGenerator();

        destination.addPlaces(placeBack);
        assertThat(destination.getPlaces()).containsOnly(placeBack);
        assertThat(placeBack.getDestination()).isEqualTo(destination);

        destination.removePlaces(placeBack);
        assertThat(destination.getPlaces()).doesNotContain(placeBack);
        assertThat(placeBack.getDestination()).isNull();

        destination.places(new HashSet<>(Set.of(placeBack)));
        assertThat(destination.getPlaces()).containsOnly(placeBack);
        assertThat(placeBack.getDestination()).isEqualTo(destination);

        destination.setPlaces(new HashSet<>());
        assertThat(destination.getPlaces()).doesNotContain(placeBack);
        assertThat(placeBack.getDestination()).isNull();
    }

    @Test
    void imagesTest() {
        Destination destination = getDestinationRandomSampleGenerator();
        ImageFile imageFileBack = getImageFileRandomSampleGenerator();

        destination.addImages(imageFileBack);
        assertThat(destination.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getDestination()).isEqualTo(destination);

        destination.removeImages(imageFileBack);
        assertThat(destination.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getDestination()).isNull();

        destination.images(new HashSet<>(Set.of(imageFileBack)));
        assertThat(destination.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getDestination()).isEqualTo(destination);

        destination.setImages(new HashSet<>());
        assertThat(destination.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getDestination()).isNull();
    }

    @Test
    void menusTest() {
        Destination destination = getDestinationRandomSampleGenerator();
        Menu menuBack = getMenuRandomSampleGenerator();

        destination.addMenus(menuBack);
        assertThat(destination.getMenus()).containsOnly(menuBack);
        assertThat(menuBack.getDestination()).isEqualTo(destination);

        destination.removeMenus(menuBack);
        assertThat(destination.getMenus()).doesNotContain(menuBack);
        assertThat(menuBack.getDestination()).isNull();

        destination.menus(new HashSet<>(Set.of(menuBack)));
        assertThat(destination.getMenus()).containsOnly(menuBack);
        assertThat(menuBack.getDestination()).isEqualTo(destination);

        destination.setMenus(new HashSet<>());
        assertThat(destination.getMenus()).doesNotContain(menuBack);
        assertThat(menuBack.getDestination()).isNull();
    }

    @Test
    void contentsTest() {
        Destination destination = getDestinationRandomSampleGenerator();
        Content contentBack = getContentRandomSampleGenerator();

        destination.addContents(contentBack);
        assertThat(destination.getContents()).containsOnly(contentBack);
        assertThat(contentBack.getDestination()).isEqualTo(destination);

        destination.removeContents(contentBack);
        assertThat(destination.getContents()).doesNotContain(contentBack);
        assertThat(contentBack.getDestination()).isNull();

        destination.contents(new HashSet<>(Set.of(contentBack)));
        assertThat(destination.getContents()).containsOnly(contentBack);
        assertThat(contentBack.getDestination()).isEqualTo(destination);

        destination.setContents(new HashSet<>());
        assertThat(destination.getContents()).doesNotContain(contentBack);
        assertThat(contentBack.getDestination()).isNull();
    }
}
