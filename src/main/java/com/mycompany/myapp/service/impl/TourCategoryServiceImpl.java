package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TourCategory;
import com.mycompany.myapp.repository.TourCategoryRepository;
import com.mycompany.myapp.service.TourCategoryService;
import com.mycompany.myapp.service.dto.TourCategoryDTO;
import com.mycompany.myapp.service.mapper.TourCategoryMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.TourCategory}.
 */
@Service
@Transactional
public class TourCategoryServiceImpl implements TourCategoryService {

    private final Logger log = LoggerFactory.getLogger(TourCategoryServiceImpl.class);

    private final TourCategoryRepository tourCategoryRepository;

    private final TourCategoryMapper tourCategoryMapper;

    public TourCategoryServiceImpl(TourCategoryRepository tourCategoryRepository, TourCategoryMapper tourCategoryMapper) {
        this.tourCategoryRepository = tourCategoryRepository;
        this.tourCategoryMapper = tourCategoryMapper;
    }

    @Override
    public TourCategoryDTO save(TourCategoryDTO tourCategoryDTO) {
        log.debug("Request to save TourCategory : {}", tourCategoryDTO);
        TourCategory tourCategory = tourCategoryMapper.toEntity(tourCategoryDTO);
        tourCategory = tourCategoryRepository.save(tourCategory);
        return tourCategoryMapper.toDto(tourCategory);
    }

    @Override
    public TourCategoryDTO update(TourCategoryDTO tourCategoryDTO) {
        log.debug("Request to update TourCategory : {}", tourCategoryDTO);
        TourCategory tourCategory = tourCategoryMapper.toEntity(tourCategoryDTO);
        tourCategory = tourCategoryRepository.save(tourCategory);
        return tourCategoryMapper.toDto(tourCategory);
    }

    @Override
    public Optional<TourCategoryDTO> partialUpdate(TourCategoryDTO tourCategoryDTO) {
        log.debug("Request to partially update TourCategory : {}", tourCategoryDTO);

        return tourCategoryRepository
            .findById(tourCategoryDTO.getId())
            .map(existingTourCategory -> {
                tourCategoryMapper.partialUpdate(existingTourCategory, tourCategoryDTO);

                return existingTourCategory;
            })
            .map(tourCategoryRepository::save)
            .map(tourCategoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TourCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TourCategories");
        return tourCategoryRepository.findAll(pageable).map(tourCategoryMapper::toDto);
    }

    public Page<TourCategoryDTO> findAllWithEagerRelationships(Pageable pageable) {
        return tourCategoryRepository.findAllWithEagerRelationships(pageable).map(tourCategoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TourCategoryDTO> findOne(Long id) {
        log.debug("Request to get TourCategory : {}", id);
        return tourCategoryRepository.findOneWithEagerRelationships(id).map(tourCategoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TourCategory : {}", id);
        tourCategoryRepository.deleteById(id);
    }
}
