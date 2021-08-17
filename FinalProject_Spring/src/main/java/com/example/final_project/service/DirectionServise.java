package com.example.final_project.service;

import com.example.final_project.entity.Direction;
import com.example.final_project.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DirectionServise {

    @Autowired
    private DirectionRepository directionRepository;


    public double getDistance(String city) {
        return getNeededDirection(city).getDistance();
    }

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
                .filter(direction -> (direction.getCityEn().toLowerCase().equals(city.toLowerCase()) || direction.getCityUk().toLowerCase().equals(city.toLowerCase())))
                .findAny().get();        //.orElse()
    }
public List<Direction> findDirectionsLike(String city){
  return !city.isEmpty() ? directionRepository.findDirectionsLike(city): directionRepository.findAll();
}


}
