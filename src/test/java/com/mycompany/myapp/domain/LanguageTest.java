package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.ContentTestSamples.*;
import static com.mycompany.myapp.domain.LanguageTestSamples.*;
import static com.mycompany.myapp.domain.PromptTestSamples.*;
import static com.mycompany.myapp.domain.TourContentTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LanguageTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Language.class);
        Language language1 = getLanguageSample1();
        Language language2 = new Language();
        assertThat(language1).isNotEqualTo(language2);

        language2.setId(language1.getId());
        assertThat(language1).isEqualTo(language2);

        language2 = getLanguageSample2();
        assertThat(language1).isNotEqualTo(language2);
    }

    @Test
    void contentTest() {
        Language language = getLanguageRandomSampleGenerator();
        Content contentBack = getContentRandomSampleGenerator();

        language.setContent(contentBack);
        assertThat(language.getContent()).isEqualTo(contentBack);
        assertThat(contentBack.getLanguage()).isEqualTo(language);

        language.content(null);
        assertThat(language.getContent()).isNull();
        assertThat(contentBack.getLanguage()).isNull();
    }

    @Test
    void tourContentTest() {
        Language language = getLanguageRandomSampleGenerator();
        TourContent tourContentBack = getTourContentRandomSampleGenerator();

        language.setTourContent(tourContentBack);
        assertThat(language.getTourContent()).isEqualTo(tourContentBack);
        assertThat(tourContentBack.getLanguage()).isEqualTo(language);

        language.tourContent(null);
        assertThat(language.getTourContent()).isNull();
        assertThat(tourContentBack.getLanguage()).isNull();
    }

    @Test
    void promptTest() {
        Language language = getLanguageRandomSampleGenerator();
        Prompt promptBack = getPromptRandomSampleGenerator();

        language.setPrompt(promptBack);
        assertThat(language.getPrompt()).isEqualTo(promptBack);
        assertThat(promptBack.getLanguage()).isEqualTo(language);

        language.prompt(null);
        assertThat(language.getPrompt()).isNull();
        assertThat(promptBack.getLanguage()).isNull();
    }
}
