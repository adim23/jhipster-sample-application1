package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.ImageFileTestSamples.*;
import static com.mycompany.myapp.domain.TagTestSamples.*;
import static com.mycompany.myapp.domain.TourExtraCategoryTestSamples.*;
import static com.mycompany.myapp.domain.TourExtraTestSamples.*;
import static com.mycompany.myapp.domain.TourTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class TourExtraTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourExtra.class);
        TourExtra tourExtra1 = getTourExtraSample1();
        TourExtra tourExtra2 = new TourExtra();
        assertThat(tourExtra1).isNotEqualTo(tourExtra2);

        tourExtra2.setId(tourExtra1.getId());
        assertThat(tourExtra1).isEqualTo(tourExtra2);

        tourExtra2 = getTourExtraSample2();
        assertThat(tourExtra1).isNotEqualTo(tourExtra2);
    }

    @Test
    void imagesTest() {
        TourExtra tourExtra = getTourExtraRandomSampleGenerator();
        ImageFile imageFileBack = getImageFileRandomSampleGenerator();

        tourExtra.addImages(imageFileBack);
        assertThat(tourExtra.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getTourExtra()).isEqualTo(tourExtra);

        tourExtra.removeImages(imageFileBack);
        assertThat(tourExtra.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getTourExtra()).isNull();

        tourExtra.images(new HashSet<>(Set.of(imageFileBack)));
        assertThat(tourExtra.getImages()).containsOnly(imageFileBack);
        assertThat(imageFileBack.getTourExtra()).isEqualTo(tourExtra);

        tourExtra.setImages(new HashSet<>());
        assertThat(tourExtra.getImages()).doesNotContain(imageFileBack);
        assertThat(imageFileBack.getTourExtra()).isNull();
    }

    @Test
    void contentsTest() {
        TourExtra tourExtra = getTourExtraRandomSampleGenerator();
        Content contentBack = getContentRandomSampleGenerator();

        tourExtra.addContents(contentBack);
        assertThat(tourExtra.getContents()).containsOnly(contentBack);
        assertThat(contentBack.getTourExtra()).isEqualTo(tourExtra);

        tourExtra.removeContents(contentBack);
        assertThat(tourExtra.getContents()).doesNotContain(contentBack);
        assertThat(contentBack.getTourExtra()).isNull();

        tourExtra.contents(new HashSet<>(Set.of(contentBack)));
        assertThat(tourExtra.getContents()).containsOnly(contentBack);
        assertThat(contentBack.getTourExtra()).isEqualTo(tourExtra);

        tourExtra.setContents(new HashSet<>());
        assertThat(tourExtra.getContents()).doesNotContain(contentBack);
        assertThat(contentBack.getTourExtra()).isNull();
    }

    @Test
    void tagsTest() {
        TourExtra tourExtra = getTourExtraRandomSampleGenerator();
        Tag tagBack = getTagRandomSampleGenerator();

        tourExtra.addTags(tagBack);
        assertThat(tourExtra.getTags()).containsOnly(tagBack);

        tourExtra.removeTags(tagBack);
        assertThat(tourExtra.getTags()).doesNotContain(tagBack);

        tourExtra.tags(new HashSet<>(Set.of(tagBack)));
        assertThat(tourExtra.getTags()).containsOnly(tagBack);

        tourExtra.setTags(new HashSet<>());
        assertThat(tourExtra.getTags()).doesNotContain(tagBack);
    }

    @Test
    void categoryTest() {
        TourExtra tourExtra = getTourExtraRandomSampleGenerator();
        TourExtraCategory tourExtraCategoryBack = getTourExtraCategoryRandomSampleGenerator();

        tourExtra.addCategory(tourExtraCategoryBack);
        assertThat(tourExtra.getCategories()).containsOnly(tourExtraCategoryBack);

        tourExtra.removeCategory(tourExtraCategoryBack);
        assertThat(tourExtra.getCategories()).doesNotContain(tourExtraCategoryBack);

        tourExtra.categories(new HashSet<>(Set.of(tourExtraCategoryBack)));
        assertThat(tourExtra.getCategories()).containsOnly(tourExtraCategoryBack);

        tourExtra.setCategories(new HashSet<>());
        assertThat(tourExtra.getCategories()).doesNotContain(tourExtraCategoryBack);
    }

    @Test
    void toursTest() {
        TourExtra tourExtra = getTourExtraRandomSampleGenerator();
        Tour tourBack = getTourRandomSampleGenerator();

        tourExtra.addTours(tourBack);
        assertThat(tourExtra.getTours()).containsOnly(tourBack);
        assertThat(tourBack.getTourExtras()).containsOnly(tourExtra);

        tourExtra.removeTours(tourBack);
        assertThat(tourExtra.getTours()).doesNotContain(tourBack);
        assertThat(tourBack.getTourExtras()).doesNotContain(tourExtra);

        tourExtra.tours(new HashSet<>(Set.of(tourBack)));
        assertThat(tourExtra.getTours()).containsOnly(tourBack);
        assertThat(tourBack.getTourExtras()).containsOnly(tourExtra);

        tourExtra.setTours(new HashSet<>());
        assertThat(tourExtra.getTours()).doesNotContain(tourBack);
        assertThat(tourBack.getTourExtras()).doesNotContain(tourExtra);
    }
}
