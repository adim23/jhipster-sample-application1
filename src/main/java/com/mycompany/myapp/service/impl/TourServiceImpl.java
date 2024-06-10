package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Tour;
import com.mycompany.myapp.repository.TourRepository;
import com.mycompany.myapp.service.TourService;
import com.mycompany.myapp.service.dto.TourDTO;
import com.mycompany.myapp.service.mapper.TourMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Tour}.
 */
@Service
@Transactional
public class TourServiceImpl implements TourService {

    private final Logger log = LoggerFactory.getLogger(TourServiceImpl.class);

    private final TourRepository tourRepository;

    private final TourMapper tourMapper;

    public TourServiceImpl(TourRepository tourRepository, TourMapper tourMapper) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    @Override
    public TourDTO save(TourDTO tourDTO) {
        log.debug("Request to save Tour : {}", tourDTO);
        Tour tour = tourMapper.toEntity(tourDTO);
        tour = tourRepository.save(tour);
        return tourMapper.toDto(tour);
    }

    @Override
    public TourDTO update(TourDTO tourDTO) {
        log.debug("Request to update Tour : {}", tourDTO);
        Tour tour = tourMapper.toEntity(tourDTO);
        tour = tourRepository.save(tour);
        return tourMapper.toDto(tour);
    }

    @Override
    public Optional<TourDTO> partialUpdate(TourDTO tourDTO) {
        log.debug("Request to partially update Tour : {}", tourDTO);

        return tourRepository
            .findById(tourDTO.getId())
            .map(existingTour -> {
                tourMapper.partialUpdate(existingTour, tourDTO);

                return existingTour;
            })
            .map(tourRepository::save)
            .map(tourMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TourDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Tours");
        return tourRepository.findAll(pageable).map(tourMapper::toDto);
    }

    public Page<TourDTO> findAllWithEagerRelationships(Pageable pageable) {
        return tourRepository.findAllWithEagerRelationships(pageable).map(tourMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TourDTO> findOne(Long id) {
        log.debug("Request to get Tour : {}", id);
        return tourRepository.findOneWithEagerRelationships(id).map(tourMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Tour : {}", id);
        tourRepository.deleteById(id);
    }
}
