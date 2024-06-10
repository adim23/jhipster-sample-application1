package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.DestinationTestSamples.*;
import static com.mycompany.myapp.domain.ImageFileTestSamples.*;
import static com.mycompany.myapp.domain.LanguageTestSamples.*;
import static com.mycompany.myapp.domain.MenuTestSamples.*;
import static com.mycompany.myapp.domain.PlaceCategoryTestSamples.*;
import static com.mycompany.myapp.domain.PlaceTestSamples.*;
import static com.mycompany.myapp.domain.PromotionTestSamples.*;
import static com.mycompany.myapp.domain.TagTestSamples.*;
import static com.mycompany.myapp.domain.TourCategoryTestSamples.*;
import static com.mycompany.myapp.domain.TourExtraCategoryTestSamples.*;
import static com.mycompany.myapp.domain.TourExtraTestSamples.*;
import static com.mycompany.myapp.domain.TourStepTestSamples.*;
import static com.mycompany.myapp.domain.WebPageTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ContentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Content.class);
        Content content1 = getContentSample1();
        Content content2 = new Content();
        assertThat(content1).isNotEqualTo(content2);

        content2.setId(content1.getId());
        assertThat(content1).isEqualTo(content2);

        content2 = getContentSample2();
        assertThat(content1).isNotEqualTo(content2);
    }

    @Test
    void languageTest() {
        Content content = getContentRandomSampleGenerator();
        Language languageBack = getLanguageRandomSampleGenerator();

        content.setLanguage(languageBack);
        assertThat(content.getLanguage()).isEqualTo(languageBack);

        content.language(null);
        assertThat(content.getLanguage()).isNull();
    }

    @Test
    void destinationTest() {
        Content content = getContentRandomSampleGenerator();
        Destination destinationBack = getDestinationRandomSampleGenerator();

        content.setDestination(destinationBack);
        assertThat(content.getDestination()).isEqualTo(destinationBack);

        content.destination(null);
        assertThat(content.getDestination()).isNull();
    }

    @Test
    void tourCategoryTest() {
        Content content = getContentRandomSampleGenerator();
        TourCategory tourCategoryBack = getTourCategoryRandomSampleGenerator();

        content.setTourCategory(tourCategoryBack);
        assertThat(content.getTourCategory()).isEqualTo(tourCategoryBack);

        content.tourCategory(null);
        assertThat(content.getTourCategory()).isNull();
    }

    @Test
    void placeTest() {
        Content content = getContentRandomSampleGenerator();
        Place placeBack = getPlaceRandomSampleGenerator();

        content.setPlace(placeBack);
        assertThat(content.getPlace()).isEqualTo(placeBack);

        content.place(null);
        assertThat(content.getPlace()).isNull();
    }

    @Test
    void placeCategoryTest() {
        Content content = getContentRandomSampleGenerator();
        PlaceCategory placeCategoryBack = getPlaceCategoryRandomSampleGenerator();

        content.setPlaceCategory(placeCategoryBack);
        assertThat(content.getPlaceCategory()).isEqualTo(placeCategoryBack);

        content.placeCategory(null);
        assertThat(content.getPlaceCategory()).isNull();
    }

    @Test
    void tourExtraCategoryTest() {
        Content content = getContentRandomSampleGenerator();
        TourExtraCategory tourExtraCategoryBack = getTourExtraCategoryRandomSampleGenerator();

        content.setTourExtraCategory(tourExtraCategoryBack);
        assertThat(content.getTourExtraCategory()).isEqualTo(tourExtraCategoryBack);

        content.tourExtraCategory(null);
        assertThat(content.getTourExtraCategory()).isNull();
    }

    @Test
    void tourExtraTest() {
        Content content = getContentRandomSampleGenerator();
        TourExtra tourExtraBack = getTourExtraRandomSampleGenerator();

        content.setTourExtra(tourExtraBack);
        assertThat(content.getTourExtra()).isEqualTo(tourExtraBack);

        content.tourExtra(null);
        assertThat(content.getTourExtra()).isNull();
    }

    @Test
    void menuTest() {
        Content content = getContentRandomSampleGenerator();
        Menu menuBack = getMenuRandomSampleGenerator();

        content.setMenu(menuBack);
        assertThat(content.getMenu()).isEqualTo(menuBack);

        content.menu(null);
        assertThat(content.getMenu()).isNull();
    }

    @Test
    void webPageTest() {
        Content content = getContentRandomSampleGenerator();
        WebPage webPageBack = getWebPageRandomSampleGenerator();

        content.setWebPage(webPageBack);
        assertThat(content.getWebPage()).isEqualTo(webPageBack);

        content.webPage(null);
        assertThat(content.getWebPage()).isNull();
    }

    @Test
    void tagTest() {
        Content content = getContentRandomSampleGenerator();
        Tag tagBack = getTagRandomSampleGenerator();

        content.setTag(tagBack);
        assertThat(content.getTag()).isEqualTo(tagBack);

        content.tag(null);
        assertThat(content.getTag()).isNull();
    }

    @Test
    void tourStepTest() {
        Content content = getContentRandomSampleGenerator();
        TourStep tourStepBack = getTourStepRandomSampleGenerator();

        content.setTourStep(tourStepBack);
        assertThat(content.getTourStep()).isEqualTo(tourStepBack);

        content.tourStep(null);
        assertThat(content.getTourStep()).isNull();
    }

    @Test
    void promotionTest() {
        Content content = getContentRandomSampleGenerator();
        Promotion promotionBack = getPromotionRandomSampleGenerator();

        content.setPromotion(promotionBack);
        assertThat(content.getPromotion()).isEqualTo(promotionBack);

        content.promotion(null);
        assertThat(content.getPromotion()).isNull();
    }

    @Test
    void imageFileTest() {
        Content content = getContentRandomSampleGenerator();
        ImageFile imageFileBack = getImageFileRandomSampleGenerator();

        content.setImageFile(imageFileBack);
        assertThat(content.getImageFile()).isEqualTo(imageFileBack);

        content.imageFile(null);
        assertThat(content.getImageFile()).isNull();
    }
}
