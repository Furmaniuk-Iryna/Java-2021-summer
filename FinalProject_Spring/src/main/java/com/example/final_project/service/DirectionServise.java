package com.example.final_project.service;

import com.example.final_project.entity.Direction;
import com.example.final_project.repository.DirectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * DirectionServise is a service we'll be using to form response and
 * where there is all the business logic for the essence Direction
 */

@Slf4j
@Service
public class DirectionServise {

    @Autowired
    private DirectionRepository directionRepository;


    public List<Direction> sortedDirectionsForUkLocale() {
        log.info("Service: started sortedDirectionsForUkLocale");
        return directionRepository.findAll().stream()
                .sorted(Comparator.comparing(Direction::getCityUk)).collect(Collectors.toList());
    }

    public List<Direction> sortedDirectionsForEnLocale() {
        log.info("Service: started sortedDirectionsForEnLocale");
        return directionRepository.findAll().stream()
                .sorted(Comparator.comparing(Direction::getCityEn)).collect(Collectors.toList());
    }

    public Direction getNeededDirection(String city) {
        log.info("Service: getNeededDirection with city {}", city);
        return directionRepository.findAll().stream()
                .filter(direction -> (direction.getCityEn().equalsIgnoreCase(city)
                        || direction.getCityUk().equalsIgnoreCase(city)))
                .findFirst().orElseThrow(() -> new RuntimeException("Incorrect city"));
    }

    public List<Direction> findDirectionsLike(String city) {
        log.info("Service: findDirectionsLike with city {}", city);
        return !city.isEmpty() ? directionRepository.findDirectionsLike(city) : directionRepository.findAll();
    }


}
