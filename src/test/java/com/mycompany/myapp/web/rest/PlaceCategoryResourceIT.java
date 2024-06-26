package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.PlaceCategoryAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.PlaceCategory;
import com.mycompany.myapp.repository.PlaceCategoryRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.PlaceCategoryService;
import com.mycompany.myapp.service.dto.PlaceCategoryDTO;
import com.mycompany.myapp.service.mapper.PlaceCategoryMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PlaceCategoryResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class PlaceCategoryResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_IMAGE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DEFAULT_IMAGE_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DEFAULT_IMAGE_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DEFAULT_IMAGE_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DEFAULT_IMAGE_DATA_CONTENT_TYPE = "image/png";

    private static final String ENTITY_API_URL = "/api/place-categories";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PlaceCategoryRepository placeCategoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private PlaceCategoryRepository placeCategoryRepositoryMock;

    @Autowired
    private PlaceCategoryMapper placeCategoryMapper;

    @Mock
    private PlaceCategoryService placeCategoryServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlaceCategoryMockMvc;

    private PlaceCategory placeCategory;

    private PlaceCategory insertedPlaceCategory;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlaceCategory createEntity(EntityManager em) {
        PlaceCategory placeCategory = new PlaceCategory()
            .code(DEFAULT_CODE)
            .enabled(DEFAULT_ENABLED)
            .icon(DEFAULT_ICON)
            .createdDate(DEFAULT_CREATED_DATE)
            .defaultImage(DEFAULT_DEFAULT_IMAGE)
            .defaultImageData(DEFAULT_DEFAULT_IMAGE_DATA)
            .defaultImageDataContentType(DEFAULT_DEFAULT_IMAGE_DATA_CONTENT_TYPE);
        return placeCategory;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlaceCategory createUpdatedEntity(EntityManager em) {
        PlaceCategory placeCategory = new PlaceCategory()
            .code(UPDATED_CODE)
            .enabled(UPDATED_ENABLED)
            .icon(UPDATED_ICON)
            .createdDate(UPDATED_CREATED_DATE)
            .defaultImage(UPDATED_DEFAULT_IMAGE)
            .defaultImageData(UPDATED_DEFAULT_IMAGE_DATA)
            .defaultImageDataContentType(UPDATED_DEFAULT_IMAGE_DATA_CONTENT_TYPE);
        return placeCategory;
    }

    @BeforeEach
    public void initTest() {
        placeCategory = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedPlaceCategory != null) {
            placeCategoryRepository.delete(insertedPlaceCategory);
            insertedPlaceCategory = null;
        }
    }

    @Test
    @Transactional
    void createPlaceCategory() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PlaceCategory
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);
        var returnedPlaceCategoryDTO = om.readValue(
            restPlaceCategoryMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(placeCategoryDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PlaceCategoryDTO.class
        );

        // Validate the PlaceCategory in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedPlaceCategory = placeCategoryMapper.toEntity(returnedPlaceCategoryDTO);
        assertPlaceCategoryUpdatableFieldsEquals(returnedPlaceCategory, getPersistedPlaceCategory(returnedPlaceCategory));

        insertedPlaceCategory = returnedPlaceCategory;
    }

    @Test
    @Transactional
    void createPlaceCategoryWithExistingId() throws Exception {
        // Create the PlaceCategory with an existing ID
        placeCategory.setId(1L);
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlaceCategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(placeCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PlaceCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        placeCategory.setCode(null);

        // Create the PlaceCategory, which fails.
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        restPlaceCategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(placeCategoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEnabledIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        placeCategory.setEnabled(null);

        // Create the PlaceCategory, which fails.
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        restPlaceCategoryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(placeCategoryDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllPlaceCategories() throws Exception {
        // Initialize the database
        insertedPlaceCategory = placeCategoryRepository.saveAndFlush(placeCategory);

        // Get all the placeCategoryList
        restPlaceCategoryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(placeCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())))
            .andExpect(jsonPath("$.[*].defaultImage").value(hasItem(DEFAULT_DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].defaultImageDataContentType").value(hasItem(DEFAULT_DEFAULT_IMAGE_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].defaultImageData").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_DEFAULT_IMAGE_DATA))));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPlaceCategoriesWithEagerRelationshipsIsEnabled() throws Exception {
        when(placeCategoryServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPlaceCategoryMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(placeCategoryServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllPlaceCategoriesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(placeCategoryServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restPlaceCategoryMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(placeCategoryRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getPlaceCategory() throws Exception {
        // Initialize the database
        insertedPlaceCategory = placeCategoryRepository.saveAndFlush(placeCategory);

        // Get the placeCategory
        restPlaceCategoryMockMvc
            .perform(get(ENTITY_API_URL_ID, placeCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(placeCategory.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()))
            .andExpect(jsonPath("$.defaultImage").value(DEFAULT_DEFAULT_IMAGE))
            .andExpect(jsonPath("$.defaultImageDataContentType").value(DEFAULT_DEFAULT_IMAGE_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.defaultImageData").value(Base64.getEncoder().encodeToString(DEFAULT_DEFAULT_IMAGE_DATA)));
    }

    @Test
    @Transactional
    void getNonExistingPlaceCategory() throws Exception {
        // Get the placeCategory
        restPlaceCategoryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPlaceCategory() throws Exception {
        // Initialize the database
        insertedPlaceCategory = placeCategoryRepository.saveAndFlush(placeCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the placeCategory
        PlaceCategory updatedPlaceCategory = placeCategoryRepository.findById(placeCategory.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPlaceCategory are not directly saved in db
        em.detach(updatedPlaceCategory);
        updatedPlaceCategory
            .code(UPDATED_CODE)
            .enabled(UPDATED_ENABLED)
            .icon(UPDATED_ICON)
            .createdDate(UPDATED_CREATED_DATE)
            .defaultImage(UPDATED_DEFAULT_IMAGE)
            .defaultImageData(UPDATED_DEFAULT_IMAGE_DATA)
            .defaultImageDataContentType(UPDATED_DEFAULT_IMAGE_DATA_CONTENT_TYPE);
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(updatedPlaceCategory);

        restPlaceCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, placeCategoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(placeCategoryDTO))
            )
            .andExpect(status().isOk());

        // Validate the PlaceCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPlaceCategoryToMatchAllProperties(updatedPlaceCategory);
    }

    @Test
    @Transactional
    void putNonExistingPlaceCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        placeCategory.setId(longCount.incrementAndGet());

        // Create the PlaceCategory
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlaceCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, placeCategoryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(placeCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlaceCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPlaceCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        placeCategory.setId(longCount.incrementAndGet());

        // Create the PlaceCategory
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlaceCategoryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(placeCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlaceCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPlaceCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        placeCategory.setId(longCount.incrementAndGet());

        // Create the PlaceCategory
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlaceCategoryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(placeCategoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlaceCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePlaceCategoryWithPatch() throws Exception {
        // Initialize the database
        insertedPlaceCategory = placeCategoryRepository.saveAndFlush(placeCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the placeCategory using partial update
        PlaceCategory partialUpdatedPlaceCategory = new PlaceCategory();
        partialUpdatedPlaceCategory.setId(placeCategory.getId());

        partialUpdatedPlaceCategory.code(UPDATED_CODE).icon(UPDATED_ICON);

        restPlaceCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlaceCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlaceCategory))
            )
            .andExpect(status().isOk());

        // Validate the PlaceCategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlaceCategoryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPlaceCategory, placeCategory),
            getPersistedPlaceCategory(placeCategory)
        );
    }

    @Test
    @Transactional
    void fullUpdatePlaceCategoryWithPatch() throws Exception {
        // Initialize the database
        insertedPlaceCategory = placeCategoryRepository.saveAndFlush(placeCategory);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the placeCategory using partial update
        PlaceCategory partialUpdatedPlaceCategory = new PlaceCategory();
        partialUpdatedPlaceCategory.setId(placeCategory.getId());

        partialUpdatedPlaceCategory
            .code(UPDATED_CODE)
            .enabled(UPDATED_ENABLED)
            .icon(UPDATED_ICON)
            .createdDate(UPDATED_CREATED_DATE)
            .defaultImage(UPDATED_DEFAULT_IMAGE)
            .defaultImageData(UPDATED_DEFAULT_IMAGE_DATA)
            .defaultImageDataContentType(UPDATED_DEFAULT_IMAGE_DATA_CONTENT_TYPE);

        restPlaceCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlaceCategory.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlaceCategory))
            )
            .andExpect(status().isOk());

        // Validate the PlaceCategory in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlaceCategoryUpdatableFieldsEquals(partialUpdatedPlaceCategory, getPersistedPlaceCategory(partialUpdatedPlaceCategory));
    }

    @Test
    @Transactional
    void patchNonExistingPlaceCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        placeCategory.setId(longCount.incrementAndGet());

        // Create the PlaceCategory
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlaceCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, placeCategoryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(placeCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlaceCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPlaceCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        placeCategory.setId(longCount.incrementAndGet());

        // Create the PlaceCategory
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlaceCategoryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(placeCategoryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlaceCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPlaceCategory() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        placeCategory.setId(longCount.incrementAndGet());

        // Create the PlaceCategory
        PlaceCategoryDTO placeCategoryDTO = placeCategoryMapper.toDto(placeCategory);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlaceCategoryMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(placeCategoryDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlaceCategory in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePlaceCategory() throws Exception {
        // Initialize the database
        insertedPlaceCategory = placeCategoryRepository.saveAndFlush(placeCategory);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the placeCategory
        restPlaceCategoryMockMvc
            .perform(delete(ENTITY_API_URL_ID, placeCategory.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return placeCategoryRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected PlaceCategory getPersistedPlaceCategory(PlaceCategory placeCategory) {
        return placeCategoryRepository.findById(placeCategory.getId()).orElseThrow();
    }

    protected void assertPersistedPlaceCategoryToMatchAllProperties(PlaceCategory expectedPlaceCategory) {
        assertPlaceCategoryAllPropertiesEquals(expectedPlaceCategory, getPersistedPlaceCategory(expectedPlaceCategory));
    }

    protected void assertPersistedPlaceCategoryToMatchUpdatableProperties(PlaceCategory expectedPlaceCategory) {
        assertPlaceCategoryAllUpdatablePropertiesEquals(expectedPlaceCategory, getPersistedPlaceCategory(expectedPlaceCategory));
    }
}
