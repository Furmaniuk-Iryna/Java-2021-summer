package com.example.final_project.service;

import com.example.final_project.entity.Direction;
import com.example.final_project.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
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
                .sorted(Comparator.comparing(Direction::getCity_uk)).collect(Collectors.toList());
    }
    public List<Direction> sortedDirectionsForEnLocale() {
        return directionRepository.findAll().stream()
                .sorted(Comparator.comparing(Direction::getCity_en)).collect(Collectors.toList());
    }
    public Direction getNeededDirection(String city) {
        return  directionRepository.findAll().stream()
                .filter(direction -> (direction.getCity_en().toLowerCase().equals(city.toLowerCase()) || direction.getCity_uk().toLowerCase().equals(city.toLowerCase())))
                .findAny().get();        //.orElse()
    }
}
