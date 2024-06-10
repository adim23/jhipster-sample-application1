package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.LanguageTestSamples.*;
import static com.mycompany.myapp.domain.TourContentTestSamples.*;
import static com.mycompany.myapp.domain.TourTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TourContentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourContent.class);
        TourContent tourContent1 = getTourContentSample1();
        TourContent tourContent2 = new TourContent();
        assertThat(tourContent1).isNotEqualTo(tourContent2);

        tourContent2.setId(tourContent1.getId());
        assertThat(tourContent1).isEqualTo(tourContent2);

        tourContent2 = getTourContentSample2();
        assertThat(tourContent1).isNotEqualTo(tourContent2);
    }

    @Test
    void languageTest() {
        TourContent tourContent = getTourContentRandomSampleGenerator();
        Language languageBack = getLanguageRandomSampleGenerator();

        tourContent.setLanguage(languageBack);
        assertThat(tourContent.getLanguage()).isEqualTo(languageBack);

        tourContent.language(null);
        assertThat(tourContent.getLanguage()).isNull();
    }

    @Test
    void tourTest() {
        TourContent tourContent = getTourContentRandomSampleGenerator();
        Tour tourBack = getTourRandomSampleGenerator();

        tourContent.setTour(tourBack);
        assertThat(tourContent.getTour()).isEqualTo(tourBack);
        assertThat(tourBack.getContent()).isEqualTo(tourContent);

        tourContent.tour(null);
        assertThat(tourContent.getTour()).isNull();
        assertThat(tourBack.getContent()).isNull();
    }
}
