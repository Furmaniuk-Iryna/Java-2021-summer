package com.example.final_project.service;

import com.example.final_project.entity.Direction;
import com.example.final_project.repository.DirectionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class DirectionServise {

    @Autowired
    private DirectionRepository directionRepository;


    public List<Direction> sortedDirectionsForUkLocale() {
        return directionRepository.findAll().stream()
                .sorted(Comparator.comparing(Direction::getCityUk)).collect(Collectors.toList());
    }

    public List<Direction> sortedDirectionsForEnLocale() {
        return directionRepository.findAll().stream()
                .sorted(Comparator.comparing(Direction::getCityEn)).collect(Collectors.toList());
    }

    public Direction getNeededDirection(String city) {
        return  directionRepository.findAll().stream()
                .filter(direction -> (direction.getCityEn().toLowerCase().equals(city.toLowerCase())
                        || direction.getCityUk().toLowerCase().equals(city.toLowerCase())))
                .findFirst().orElseThrow(()->new RuntimeException("Incorrect city"));
    }

    public List<Direction> findDirectionsLike(String city){
      return !city.isEmpty() ? directionRepository.findDirectionsLike(city): directionRepository.findAll();
    }


}
