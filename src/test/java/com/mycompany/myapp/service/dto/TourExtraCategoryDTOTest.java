package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TourExtraCategoryDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TourExtraCategoryDTO.class);
        TourExtraCategoryDTO tourExtraCategoryDTO1 = new TourExtraCategoryDTO();
        tourExtraCategoryDTO1.setId(1L);
        TourExtraCategoryDTO tourExtraCategoryDTO2 = new TourExtraCategoryDTO();
        assertThat(tourExtraCategoryDTO1).isNotEqualTo(tourExtraCategoryDTO2);
        tourExtraCategoryDTO2.setId(tourExtraCategoryDTO1.getId());
        assertThat(tourExtraCategoryDTO1).isEqualTo(tourExtraCategoryDTO2);
        tourExtraCategoryDTO2.setId(2L);
        assertThat(tourExtraCategoryDTO1).isNotEqualTo(tourExtraCategoryDTO2);
        tourExtraCategoryDTO1.setId(null);
        assertThat(tourExtraCategoryDTO1).isNotEqualTo(tourExtraCategoryDTO2);
    }
}
