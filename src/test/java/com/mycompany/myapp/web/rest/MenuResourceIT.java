package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.MenuAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Menu;
import com.mycompany.myapp.repository.MenuRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.service.MenuService;
import com.mycompany.myapp.service.dto.MenuDTO;
import com.mycompany.myapp.service.mapper.MenuMapper;
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
 * Integration tests for the {@link MenuResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class MenuResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ENABLED = false;
    private static final Boolean UPDATED_ENABLED = true;

    private static final String DEFAULT_ICON = "AAAAAAAAAA";
    private static final String UPDATED_ICON = "BBBBBBBBBB";

    private static final String DEFAULT_URI = "AAAAAAAAAA";
    private static final String UPDATED_URI = "BBBBBBBBBB";

    private static final String DEFAULT_DEFAULT_IMAGE = "AAAAAAAAAA";
    private static final String UPDATED_DEFAULT_IMAGE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_DEFAULT_IMAGE_DATA = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DEFAULT_IMAGE_DATA = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_DEFAULT_IMAGE_DATA_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DEFAULT_IMAGE_DATA_CONTENT_TYPE = "image/png";

    private static final LocalDate DEFAULT_CREATED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/menus";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private UserRepository userRepository;

    @Mock
    private MenuRepository menuRepositoryMock;

    @Autowired
    private MenuMapper menuMapper;

    @Mock
    private MenuService menuServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMenuMockMvc;

    private Menu menu;

    private Menu insertedMenu;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Menu createEntity(EntityManager em) {
        Menu menu = new Menu()
            .code(DEFAULT_CODE)
            .enabled(DEFAULT_ENABLED)
            .icon(DEFAULT_ICON)
            .uri(DEFAULT_URI)
            .defaultImage(DEFAULT_DEFAULT_IMAGE)
            .defaultImageData(DEFAULT_DEFAULT_IMAGE_DATA)
            .defaultImageDataContentType(DEFAULT_DEFAULT_IMAGE_DATA_CONTENT_TYPE)
            .createdDate(DEFAULT_CREATED_DATE);
        return menu;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Menu createUpdatedEntity(EntityManager em) {
        Menu menu = new Menu()
            .code(UPDATED_CODE)
            .enabled(UPDATED_ENABLED)
            .icon(UPDATED_ICON)
            .uri(UPDATED_URI)
            .defaultImage(UPDATED_DEFAULT_IMAGE)
            .defaultImageData(UPDATED_DEFAULT_IMAGE_DATA)
            .defaultImageDataContentType(UPDATED_DEFAULT_IMAGE_DATA_CONTENT_TYPE)
            .createdDate(UPDATED_CREATED_DATE);
        return menu;
    }

    @BeforeEach
    public void initTest() {
        menu = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedMenu != null) {
            menuRepository.delete(insertedMenu);
            insertedMenu = null;
        }
    }

    @Test
    @Transactional
    void createMenu() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);
        var returnedMenuDTO = om.readValue(
            restMenuMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            MenuDTO.class
        );

        // Validate the Menu in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedMenu = menuMapper.toEntity(returnedMenuDTO);
        assertMenuUpdatableFieldsEquals(returnedMenu, getPersistedMenu(returnedMenu));

        insertedMenu = returnedMenu;
    }

    @Test
    @Transactional
    void createMenuWithExistingId() throws Exception {
        // Create the Menu with an existing ID
        menu.setId(1L);
        MenuDTO menuDTO = menuMapper.toDto(menu);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkCodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        menu.setCode(null);

        // Create the Menu, which fails.
        MenuDTO menuDTO = menuMapper.toDto(menu);

        restMenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEnabledIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        menu.setEnabled(null);

        // Create the Menu, which fails.
        MenuDTO menuDTO = menuMapper.toDto(menu);

        restMenuMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllMenus() throws Exception {
        // Initialize the database
        insertedMenu = menuRepository.saveAndFlush(menu);

        // Get all the menuList
        restMenuMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menu.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].enabled").value(hasItem(DEFAULT_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].icon").value(hasItem(DEFAULT_ICON)))
            .andExpect(jsonPath("$.[*].uri").value(hasItem(DEFAULT_URI)))
            .andExpect(jsonPath("$.[*].defaultImage").value(hasItem(DEFAULT_DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.[*].defaultImageDataContentType").value(hasItem(DEFAULT_DEFAULT_IMAGE_DATA_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].defaultImageData").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_DEFAULT_IMAGE_DATA))))
            .andExpect(jsonPath("$.[*].createdDate").value(hasItem(DEFAULT_CREATED_DATE.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllMenusWithEagerRelationshipsIsEnabled() throws Exception {
        when(menuServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMenuMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(menuServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllMenusWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(menuServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restMenuMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(menuRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getMenu() throws Exception {
        // Initialize the database
        insertedMenu = menuRepository.saveAndFlush(menu);

        // Get the menu
        restMenuMockMvc
            .perform(get(ENTITY_API_URL_ID, menu.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(menu.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.enabled").value(DEFAULT_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.icon").value(DEFAULT_ICON))
            .andExpect(jsonPath("$.uri").value(DEFAULT_URI))
            .andExpect(jsonPath("$.defaultImage").value(DEFAULT_DEFAULT_IMAGE))
            .andExpect(jsonPath("$.defaultImageDataContentType").value(DEFAULT_DEFAULT_IMAGE_DATA_CONTENT_TYPE))
            .andExpect(jsonPath("$.defaultImageData").value(Base64.getEncoder().encodeToString(DEFAULT_DEFAULT_IMAGE_DATA)))
            .andExpect(jsonPath("$.createdDate").value(DEFAULT_CREATED_DATE.toString()));
    }

    @Test
    @Transactional
    void getNonExistingMenu() throws Exception {
        // Get the menu
        restMenuMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMenu() throws Exception {
        // Initialize the database
        insertedMenu = menuRepository.saveAndFlush(menu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the menu
        Menu updatedMenu = menuRepository.findById(menu.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMenu are not directly saved in db
        em.detach(updatedMenu);
        updatedMenu
            .code(UPDATED_CODE)
            .enabled(UPDATED_ENABLED)
            .icon(UPDATED_ICON)
            .uri(UPDATED_URI)
            .defaultImage(UPDATED_DEFAULT_IMAGE)
            .defaultImageData(UPDATED_DEFAULT_IMAGE_DATA)
            .defaultImageDataContentType(UPDATED_DEFAULT_IMAGE_DATA_CONTENT_TYPE)
            .createdDate(UPDATED_CREATED_DATE);
        MenuDTO menuDTO = menuMapper.toDto(updatedMenu);

        restMenuMockMvc
            .perform(put(ENTITY_API_URL_ID, menuDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuDTO)))
            .andExpect(status().isOk());

        // Validate the Menu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMenuToMatchAllProperties(updatedMenu);
    }

    @Test
    @Transactional
    void putNonExistingMenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menu.setId(longCount.incrementAndGet());

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(put(ENTITY_API_URL_ID, menuDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menu.setId(longCount.incrementAndGet());

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(menuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menu.setId(longCount.incrementAndGet());

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(menuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Menu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMenuWithPatch() throws Exception {
        // Initialize the database
        insertedMenu = menuRepository.saveAndFlush(menu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the menu using partial update
        Menu partialUpdatedMenu = new Menu();
        partialUpdatedMenu.setId(menu.getId());

        partialUpdatedMenu.enabled(UPDATED_ENABLED).createdDate(UPDATED_CREATED_DATE);

        restMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMenu))
            )
            .andExpect(status().isOk());

        // Validate the Menu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMenuUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedMenu, menu), getPersistedMenu(menu));
    }

    @Test
    @Transactional
    void fullUpdateMenuWithPatch() throws Exception {
        // Initialize the database
        insertedMenu = menuRepository.saveAndFlush(menu);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the menu using partial update
        Menu partialUpdatedMenu = new Menu();
        partialUpdatedMenu.setId(menu.getId());

        partialUpdatedMenu
            .code(UPDATED_CODE)
            .enabled(UPDATED_ENABLED)
            .icon(UPDATED_ICON)
            .uri(UPDATED_URI)
            .defaultImage(UPDATED_DEFAULT_IMAGE)
            .defaultImageData(UPDATED_DEFAULT_IMAGE_DATA)
            .defaultImageDataContentType(UPDATED_DEFAULT_IMAGE_DATA_CONTENT_TYPE)
            .createdDate(UPDATED_CREATED_DATE);

        restMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMenu.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMenu))
            )
            .andExpect(status().isOk());

        // Validate the Menu in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMenuUpdatableFieldsEquals(partialUpdatedMenu, getPersistedMenu(partialUpdatedMenu));
    }

    @Test
    @Transactional
    void patchNonExistingMenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menu.setId(longCount.incrementAndGet());

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, menuDTO.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(menuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menu.setId(longCount.incrementAndGet());

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(menuDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Menu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMenu() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        menu.setId(longCount.incrementAndGet());

        // Create the Menu
        MenuDTO menuDTO = menuMapper.toDto(menu);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMenuMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(menuDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Menu in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMenu() throws Exception {
        // Initialize the database
        insertedMenu = menuRepository.saveAndFlush(menu);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the menu
        restMenuMockMvc
            .perform(delete(ENTITY_API_URL_ID, menu.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return menuRepository.count();
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

    protected Menu getPersistedMenu(Menu menu) {
        return menuRepository.findById(menu.getId()).orElseThrow();
    }

    protected void assertPersistedMenuToMatchAllProperties(Menu expectedMenu) {
        assertMenuAllPropertiesEquals(expectedMenu, getPersistedMenu(expectedMenu));
    }

    protected void assertPersistedMenuToMatchUpdatableProperties(Menu expectedMenu) {
        assertMenuAllUpdatablePropertiesEquals(expectedMenu, getPersistedMenu(expectedMenu));
    }
}
