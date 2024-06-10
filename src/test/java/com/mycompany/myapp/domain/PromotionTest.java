package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.PromotionTestSamples.*;
import static com.mycompany.myapp.domain.TourTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PromotionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Promotion.class);
        Promotion promotion1 = getPromotionSample1();
        Promotion promotion2 = new Promotion();
        assertThat(promotion1).isNotEqualTo(promotion2);

        promotion2.setId(promotion1.getId());
        assertThat(promotion1).isEqualTo(promotion2);

        promotion2 = getPromotionSample2();
        assertThat(promotion1).isNotEqualTo(promotion2);
    }

    @Test
    void titleTest() {
        Promotion promotion = getPromotionRandomSampleGenerator();
        Content contentBack = getContentRandomSampleGenerator();

        promotion.addTitle(contentBack);
        assertThat(promotion.getTitles()).containsOnly(contentBack);
        assertThat(contentBack.getPromotion()).isEqualTo(promotion);

        promotion.removeTitle(contentBack);
        assertThat(promotion.getTitles()).doesNotContain(contentBack);
        assertThat(contentBack.getPromotion()).isNull();

        promotion.titles(new HashSet<>(Set.of(contentBack)));
        assertThat(promotion.getTitles()).containsOnly(contentBack);
        assertThat(contentBack.getPromotion()).isEqualTo(promotion);

        promotion.setTitles(new HashSet<>());
        assertThat(promotion.getTitles()).doesNotContain(contentBack);
        assertThat(contentBack.getPromotion()).isNull();
    }

    @Test
    void tourTest() {
        Promotion promotion = getPromotionRandomSampleGenerator();
        Tour tourBack = getTourRandomSampleGenerator();

        promotion.addTour(tourBack);
        assertThat(promotion.getTours()).containsOnly(tourBack);
        assertThat(tourBack.getPromotions()).containsOnly(promotion);

        promotion.removeTour(tourBack);
        assertThat(promotion.getTours()).doesNotContain(tourBack);
        assertThat(tourBack.getPromotions()).doesNotContain(promotion);

        promotion.tours(new HashSet<>(Set.of(tourBack)));
        assertThat(promotion.getTours()).containsOnly(tourBack);
        assertThat(tourBack.getPromotions()).containsOnly(promotion);

        promotion.setTours(new HashSet<>());
        assertThat(promotion.getTours()).doesNotContain(tourBack);
        assertThat(tourBack.getPromotions()).doesNotContain(promotion);
    }
}
