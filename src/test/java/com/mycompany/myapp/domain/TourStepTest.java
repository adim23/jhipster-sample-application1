package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.PlaceTestSamples.*;
import static com.mycompany.myapp.domain.TourStepTestSamples.*;
import static com.mycompany.myapp.domain.TourTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TourStepTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourStep.class);
        TourStep tourStep1 = getTourStepSample1();
        TourStep tourStep2 = new TourStep();
        assertThat(tourStep1).isNotEqualTo(tourStep2);

        tourStep2.setId(tourStep1.getId());
        assertThat(tourStep1).isEqualTo(tourStep2);

        tourStep2 = getTourStepSample2();
        assertThat(tourStep1).isNotEqualTo(tourStep2);
    }

    @Test
    void infoTest() {
        TourStep tourStep = getTourStepRandomSampleGenerator();
        Content contentBack = getContentRandomSampleGenerator();

        tourStep.addInfo(contentBack);
        assertThat(tourStep.getInfos()).containsOnly(contentBack);
        assertThat(contentBack.getTourStep()).isEqualTo(tourStep);

        tourStep.removeInfo(contentBack);
        assertThat(tourStep.getInfos()).doesNotContain(contentBack);
        assertThat(contentBack.getTourStep()).isNull();

        tourStep.infos(new HashSet<>(Set.of(contentBack)));
        assertThat(tourStep.getInfos()).containsOnly(contentBack);
        assertThat(contentBack.getTourStep()).isEqualTo(tourStep);

        tourStep.setInfos(new HashSet<>());
        assertThat(tourStep.getInfos()).doesNotContain(contentBack);
        assertThat(contentBack.getTourStep()).isNull();
    }

    @Test
    void tourTest() {
        TourStep tourStep = getTourStepRandomSampleGenerator();
        Tour tourBack = getTourRandomSampleGenerator();

        tourStep.setTour(tourBack);
        assertThat(tourStep.getTour()).isEqualTo(tourBack);

        tourStep.tour(null);
        assertThat(tourStep.getTour()).isNull();
    }

    @Test
    void placeTest() {
        TourStep tourStep = getTourStepRandomSampleGenerator();
        Place placeBack = getPlaceRandomSampleGenerator();

        tourStep.setPlace(placeBack);
        assertThat(tourStep.getPlace()).isEqualTo(placeBack);

        tourStep.place(null);
        assertThat(tourStep.getPlace()).isNull();
    }
}
