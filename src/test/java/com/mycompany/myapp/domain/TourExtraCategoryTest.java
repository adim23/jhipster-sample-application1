package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.ImageFileTestSamples.*;
import static com.mycompany.myapp.domain.TourExtraCategoryTestSamples.*;
import static com.mycompany.myapp.domain.TourExtraTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TourExtraCategoryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourExtraCategory.class);
        TourExtraCategory tourExtraCategory1 = getTourExtraCategorySample1();
        TourExtraCategory tourExtraCategory2 = new TourExtraCategory();
        assertThat(tourExtraCategory1).isNotEqualTo(tourExtraCategory2);

        tourExtraCategory2.setId(tourExtraCategory1.getId());
        assertThat(tourExtraCategory1).isEqualTo(tourExtraCategory2);

        tourExtraCategory2 = getTourExtraCategorySample2();
        assertThat(tourExtraCategory1).isNotEqualTo(tourExtraCategory2);
    }

    @Test
    void imagesTest() {
        TourExtraCategory tourExtraCategory = getTourExtraCategoryRandomSampleGenerator();
        ImageFile imageFileBack = getImageFileRandomSampleGenerator();

        tourExtraCategory.addImages(imageFileBack);
        assertThat(tourExtraCategory.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getTourExtraCategory()).isEqualTo(tourExtraCategory);

        tourExtraCategory.removeImages(imageFileBack);
        assertThat(tourExtraCategory.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getTourExtraCategory()).isNull();

        tourExtraCategory.images(new HashSet<>(Set.of(imageFileBack)));
        assertThat(tourExtraCategory.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getTourExtraCategory()).isEqualTo(tourExtraCategory);

        tourExtraCategory.setImages(new HashSet<>());
        assertThat(tourExtraCategory.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getTourExtraCategory()).isNull();
    }

    @Test
    void contentsTest() {
        TourExtraCategory tourExtraCategory = getTourExtraCategoryRandomSampleGenerator();
        Content contentBack = getContentRandomSampleGenerator();

        tourExtraCategory.addContents(contentBack);
        assertThat(tourExtraCategory.getContents()).containsOnly(contentBack);
        assertThat(contentBack.getTourExtraCategory()).isEqualTo(tourExtraCategory);

        tourExtraCategory.removeContents(contentBack);
        assertThat(tourExtraCategory.getContents()).doesNotContain(contentBack);
        assertThat(contentBack.getTourExtraCategory()).isNull();

        tourExtraCategory.contents(new HashSet<>(Set.of(contentBack)));
        assertThat(tourExtraCategory.getContents()).containsOnly(contentBack);
        assertThat(contentBack.getTourExtraCategory()).isEqualTo(tourExtraCategory);

        tourExtraCategory.setContents(new HashSet<>());
        assertThat(tourExtraCategory.getContents()).doesNotContain(contentBack);
        assertThat(contentBack.getTourExtraCategory()).isNull();
    }

    @Test
    void extraTest() {
        TourExtraCategory tourExtraCategory = getTourExtraCategoryRandomSampleGenerator();
        TourExtra tourExtraBack = getTourExtraRandomSampleGenerator();

        tourExtraCategory.addExtra(tourExtraBack);
        assertThat(tourExtraCategory.getExtras()).containsOnly(tourExtraBack);
        assertThat(tourExtraBack.getCategories()).containsOnly(tourExtraCategory);

        tourExtraCategory.removeExtra(tourExtraBack);
        assertThat(tourExtraCategory.getExtras()).doesNotContain(tourExtraBack);
        assertThat(tourExtraBack.getCategories()).doesNotContain(tourExtraCategory);

        tourExtraCategory.extras(new HashSet<>(Set.of(tourExtraBack)));
        assertThat(tourExtraCategory.getExtras()).containsOnly(tourExtraBack);
        assertThat(tourExtraBack.getCategories()).containsOnly(tourExtraCategory);

        tourExtraCategory.setExtras(new HashSet<>());
        assertThat(tourExtraCategory.getExtras()).doesNotContain(tourExtraBack);
        assertThat(tourExtraBack.getCategories()).doesNotContain(tourExtraCategory);
    }
}
