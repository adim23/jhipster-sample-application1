package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.PlaceCategory;
import com.mycompany.myapp.repository.PlaceCategoryRepository;
import com.mycompany.myapp.service.PlaceCategoryService;
import com.mycompany.myapp.service.dto.PlaceCategoryDTO;
import com.mycompany.myapp.service.mapper.PlaceCategoryMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.PlaceCategory}.
 */
@Service
@Transactional
public class PlaceCategoryServiceImpl implements PlaceCategoryService {

    private final Logger log = LoggerFactory.getLogger(PlaceCategoryServiceImpl.class);

    private final PlaceCategoryRepository placeCategoryRepository;

    private final PlaceCategoryMapper placeCategoryMapper;

    public PlaceCategoryServiceImpl(PlaceCategoryRepository placeCategoryRepository, PlaceCategoryMapper placeCategoryMapper) {
        this.placeCategoryRepository = placeCategoryRepository;
        this.placeCategoryMapper = placeCategoryMapper;
    }

    @Override
    public PlaceCategoryDTO save(PlaceCategoryDTO placeCategoryDTO) {
        log.debug("Request to save PlaceCategory : {}", placeCategoryDTO);
        PlaceCategory placeCategory = placeCategoryMapper.toEntity(placeCategoryDTO);
        placeCategory = placeCategoryRepository.save(placeCategory);
        return placeCategoryMapper.toDto(placeCategory);
    }

    @Override
    public PlaceCategoryDTO update(PlaceCategoryDTO placeCategoryDTO) {
        log.debug("Request to update PlaceCategory : {}", placeCategoryDTO);
        PlaceCategory placeCategory = placeCategoryMapper.toEntity(placeCategoryDTO);
        placeCategory = placeCategoryRepository.save(placeCategory);
        return placeCategoryMapper.toDto(placeCategory);
    }

    @Override
    public Optional<PlaceCategoryDTO> partialUpdate(PlaceCategoryDTO placeCategoryDTO) {
        log.debug("Request to partially update PlaceCategory : {}", placeCategoryDTO);

        return placeCategoryRepository
            .findById(placeCategoryDTO.getId())
            .map(existingPlaceCategory -> {
                placeCategoryMapper.partialUpdate(existingPlaceCategory, placeCategoryDTO);

                return existingPlaceCategory;
            })
            .map(placeCategoryRepository::save)
            .map(placeCategoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PlaceCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PlaceCategories");
        return placeCategoryRepository.findAll(pageable).map(placeCategoryMapper::toDto);
    }

    public Page<PlaceCategoryDTO> findAllWithEagerRelationships(Pageable pageable) {
        return placeCategoryRepository.findAllWithEagerRelationships(pageable).map(placeCategoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PlaceCategoryDTO> findOne(Long id) {
        log.debug("Request to get PlaceCategory : {}", id);
        return placeCategoryRepository.findOneWithEagerRelationships(id).map(placeCategoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PlaceCategory : {}", id);
        placeCategoryRepository.deleteById(id);
    }
}
