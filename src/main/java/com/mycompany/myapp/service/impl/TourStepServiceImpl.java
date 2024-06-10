package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TourStep;
import com.mycompany.myapp.repository.TourStepRepository;
import com.mycompany.myapp.service.TourStepService;
import com.mycompany.myapp.service.dto.TourStepDTO;
import com.mycompany.myapp.service.mapper.TourStepMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.TourStep}.
 */
@Service
@Transactional
public class TourStepServiceImpl implements TourStepService {

    private final Logger log = LoggerFactory.getLogger(TourStepServiceImpl.class);

    private final TourStepRepository tourStepRepository;

    private final TourStepMapper tourStepMapper;

    public TourStepServiceImpl(TourStepRepository tourStepRepository, TourStepMapper tourStepMapper) {
        this.tourStepRepository = tourStepRepository;
        this.tourStepMapper = tourStepMapper;
    }

    @Override
    public TourStepDTO save(TourStepDTO tourStepDTO) {
        log.debug("Request to save TourStep : {}", tourStepDTO);
        TourStep tourStep = tourStepMapper.toEntity(tourStepDTO);
        tourStep = tourStepRepository.save(tourStep);
        return tourStepMapper.toDto(tourStep);
    }

    @Override
    public TourStepDTO update(TourStepDTO tourStepDTO) {
        log.debug("Request to update TourStep : {}", tourStepDTO);
        TourStep tourStep = tourStepMapper.toEntity(tourStepDTO);
        tourStep = tourStepRepository.save(tourStep);
        return tourStepMapper.toDto(tourStep);
    }

    @Override
    public Optional<TourStepDTO> partialUpdate(TourStepDTO tourStepDTO) {
        log.debug("Request to partially update TourStep : {}", tourStepDTO);

        return tourStepRepository
            .findById(tourStepDTO.getId())
            .map(existingTourStep -> {
                tourStepMapper.partialUpdate(existingTourStep, tourStepDTO);

                return existingTourStep;
            })
            .map(tourStepRepository::save)
            .map(tourStepMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TourStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TourSteps");
        return tourStepRepository.findAll(pageable).map(tourStepMapper::toDto);
    }

    public Page<TourStepDTO> findAllWithEagerRelationships(Pageable pageable) {
        return tourStepRepository.findAllWithEagerRelationships(pageable).map(tourStepMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TourStepDTO> findOne(Long id) {
        log.debug("Request to get TourStep : {}", id);
        return tourStepRepository.findOneWithEagerRelationships(id).map(tourStepMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TourStep : {}", id);
        tourStepRepository.deleteById(id);
    }
}
