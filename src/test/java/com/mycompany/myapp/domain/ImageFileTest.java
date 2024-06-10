package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.DestinationTestSamples.*;
import static com.mycompany.myapp.domain.DriverTestSamples.*;
import static com.mycompany.myapp.domain.ImageFileTestSamples.*;
import static com.mycompany.myapp.domain.PlaceCategoryTestSamples.*;
import static com.mycompany.myapp.domain.PlaceTestSamples.*;
import static com.mycompany.myapp.domain.TourCategoryTestSamples.*;
import static com.mycompany.myapp.domain.TourExtraCategoryTestSamples.*;
import static com.mycompany.myapp.domain.TourExtraTestSamples.*;
import static com.mycompany.myapp.domain.TourTestSamples.*;
import static com.mycompany.myapp.domain.VehicleTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ImageFileTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ImageFile.class);
        ImageFile imageFile1 = getImageFileSample1();
        ImageFile imageFile2 = new ImageFile();
        assertThat(imageFile1).isNotEqualTo(imageFile2);

        imageFile2.setId(imageFile1.getId());
        assertThat(imageFile1).isEqualTo(imageFile2);

        imageFile2 = getImageFileSample2();
        assertThat(imageFile1).isNotEqualTo(imageFile2);
    }

    @Test
    void captionsTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        Content contentBack = getContentRandomSampleGenerator();

        imageFile.addCaptions(contentBack);
        assertThat(imageFile.getCaptions()).containsOnly(contentBack);
        assertThat(contentBack.getImageFile()).isEqualTo(imageFile);

        imageFile.removeCaptions(contentBack);
        assertThat(imageFile.getCaptions()).doesNotContain(contentBack);
        assertThat(contentBack.getImageFile()).isNull();

        imageFile.captions(new HashSet<>(Set.of(contentBack)));
        assertThat(imageFile.getCaptions()).containsOnly(contentBack);
        assertThat(contentBack.getImageFile()).isEqualTo(imageFile);

        imageFile.setCaptions(new HashSet<>());
        assertThat(imageFile.getCaptions()).doesNotContain(contentBack);
        assertThat(contentBack.getImageFile()).isNull();
    }

    @Test
    void destinationTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        Destination destinationBack = getDestinationRandomSampleGenerator();

        imageFile.setDestination(destinationBack);
        assertThat(imageFile.getDestination()).isEqualTo(destinationBack);

        imageFile.destination(null);
        assertThat(imageFile.getDestination()).isNull();
    }

    @Test
    void tourTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        Tour tourBack = getTourRandomSampleGenerator();

        imageFile.setTour(tourBack);
        assertThat(imageFile.getTour()).isEqualTo(tourBack);

        imageFile.tour(null);
        assertThat(imageFile.getTour()).isNull();
    }

    @Test
    void tourCategoryTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        TourCategory tourCategoryBack = getTourCategoryRandomSampleGenerator();

        imageFile.setTourCategory(tourCategoryBack);
        assertThat(imageFile.getTourCategory()).isEqualTo(tourCategoryBack);

        imageFile.tourCategory(null);
        assertThat(imageFile.getTourCategory()).isNull();
    }

    @Test
    void placeTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        Place placeBack = getPlaceRandomSampleGenerator();

        imageFile.setPlace(placeBack);
        assertThat(imageFile.getPlace()).isEqualTo(placeBack);

        imageFile.place(null);
        assertThat(imageFile.getPlace()).isNull();
    }

    @Test
    void placeCategoryTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        PlaceCategory placeCategoryBack = getPlaceCategoryRandomSampleGenerator();

        imageFile.setPlaceCategory(placeCategoryBack);
        assertThat(imageFile.getPlaceCategory()).isEqualTo(placeCategoryBack);

        imageFile.placeCategory(null);
        assertThat(imageFile.getPlaceCategory()).isNull();
    }

    @Test
    void tourExtraCategoryTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        TourExtraCategory tourExtraCategoryBack = getTourExtraCategoryRandomSampleGenerator();

        imageFile.setTourExtraCategory(tourExtraCategoryBack);
        assertThat(imageFile.getTourExtraCategory()).isEqualTo(tourExtraCategoryBack);

        imageFile.tourExtraCategory(null);
        assertThat(imageFile.getTourExtraCategory()).isNull();
    }

    @Test
    void tourExtraTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        TourExtra tourExtraBack = getTourExtraRandomSampleGenerator();

        imageFile.setTourExtra(tourExtraBack);
        assertThat(imageFile.getTourExtra()).isEqualTo(tourExtraBack);

        imageFile.tourExtra(null);
        assertThat(imageFile.getTourExtra()).isNull();
    }

    @Test
    void vehicleTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        Vehicle vehicleBack = getVehicleRandomSampleGenerator();

        imageFile.setVehicle(vehicleBack);
        assertThat(imageFile.getVehicle()).isEqualTo(vehicleBack);

        imageFile.vehicle(null);
        assertThat(imageFile.getVehicle()).isNull();
    }

    @Test
    void driverTest() {
        ImageFile imageFile = getImageFileRandomSampleGenerator();
        Driver driverBack = getDriverRandomSampleGenerator();

        imageFile.setDriver(driverBack);
        assertThat(imageFile.getDriver()).isEqualTo(driverBack);

        imageFile.driver(null);
        assertThat(imageFile.getDriver()).isNull();
    }
}
