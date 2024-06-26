package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ImageFileRepository;
import com.mycompany.myapp.service.ImageFileService;
import com.mycompany.myapp.service.dto.ImageFileDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.ImageFile}.
 */
@RestController
@RequestMapping("/api/image-files")
public class ImageFileResource {

    private final Logger log = LoggerFactory.getLogger(ImageFileResource.class);

    private static final String ENTITY_NAME = "imageFile";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ImageFileService imageFileService;

    private final ImageFileRepository imageFileRepository;

    public ImageFileResource(ImageFileService imageFileService, ImageFileRepository imageFileRepository) {
        this.imageFileService = imageFileService;
        this.imageFileRepository = imageFileRepository;
    }

    /**
     * {@code POST  /image-files} : Create a new imageFile.
     *
     * @param imageFileDTO the imageFileDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new imageFileDTO, or with status {@code 400 (Bad Request)} if the imageFile has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ImageFileDTO> createImageFile(@Valid @RequestBody ImageFileDTO imageFileDTO) throws URISyntaxException {
        log.debug("REST request to save ImageFile : {}", imageFileDTO);
        if (imageFileDTO.getId() != null) {
            throw new BadRequestAlertException("A new imageFile cannot already have an ID", ENTITY_NAME, "idexists");
        }
        imageFileDTO = imageFileService.save(imageFileDTO);
        return ResponseEntity.created(new URI("/api/image-files/" + imageFileDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, imageFileDTO.getId().toString()))
            .body(imageFileDTO);
    }

    /**
     * {@code PUT  /image-files/:id} : Updates an existing imageFile.
     *
     * @param id the id of the imageFileDTO to save.
     * @param imageFileDTO the imageFileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageFileDTO,
     * or with status {@code 400 (Bad Request)} if the imageFileDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the imageFileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ImageFileDTO> updateImageFile(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ImageFileDTO imageFileDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ImageFile : {}, {}", id, imageFileDTO);
        if (imageFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, imageFileDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!imageFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        imageFileDTO = imageFileService.update(imageFileDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageFileDTO.getId().toString()))
            .body(imageFileDTO);
    }

    /**
     * {@code PATCH  /image-files/:id} : Partial updates given fields of an existing imageFile, field will ignore if it is null
     *
     * @param id the id of the imageFileDTO to save.
     * @param imageFileDTO the imageFileDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated imageFileDTO,
     * or with status {@code 400 (Bad Request)} if the imageFileDTO is not valid,
     * or with status {@code 404 (Not Found)} if the imageFileDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the imageFileDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ImageFileDTO> partialUpdateImageFile(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ImageFileDTO imageFileDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ImageFile partially : {}, {}", id, imageFileDTO);
        if (imageFileDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, imageFileDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!imageFileRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ImageFileDTO> result = imageFileService.partialUpdate(imageFileDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, imageFileDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /image-files} : get all the imageFiles.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of imageFiles in body.
     */
    @GetMapping("")
    public ResponseEntity<List<ImageFileDTO>> getAllImageFiles(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get a page of ImageFiles");
        Page<ImageFileDTO> page;
        if (eagerload) {
            page = imageFileService.findAllWithEagerRelationships(pageable);
        } else {
            page = imageFileService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /image-files/:id} : get the "id" imageFile.
     *
     * @param id the id of the imageFileDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the imageFileDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ImageFileDTO> getImageFile(@PathVariable("id") Long id) {
        log.debug("REST request to get ImageFile : {}", id);
        Optional<ImageFileDTO> imageFileDTO = imageFileService.findOne(id);
        return ResponseUtil.wrapOrNotFound(imageFileDTO);
    }

    /**
     * {@code DELETE  /image-files/:id} : delete the "id" imageFile.
     *
     * @param id the id of the imageFileDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImageFile(@PathVariable("id") Long id) {
        log.debug("REST request to delete ImageFile : {}", id);
        imageFileService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
