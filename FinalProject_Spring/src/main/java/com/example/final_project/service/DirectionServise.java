package com.example.final_project.service;

import com.example.final_project.entity.Direction;
import com.example.final_project.repository.DirectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class DirectionServise {

    @Autowired
    private DirectionRepository directionRepository;

    //TODO optional
    public double getDistance(String city) {
        ArrayList<Direction> directionList = (ArrayList<Direction>) directionRepository.findAll();
        Direction neededDirection = directionList.stream()
                .filter(direction -> (direction.getCity_en().equals(city) || direction.getCity_uk().equals(city)))
                .findAny().get();        //.orElse()
        return neededDirection.getDistance();
    }

    public ArrayList<Direction> sortedDirectionsForUkLocale() {
        return (ArrayList<Direction>) directionRepository.findAll().stream()
                .sorted(Comparator.comparing(Direction::getCity_uk)).collect(Collectors.toList());
    }
    public ArrayList<Direction> sortedDirectionsForEnLocale() {
        return (ArrayList<Direction>) directionRepository.findAll().stream()
                .sorted(Comparator.comparing(Direction::getCity_en)).collect(Collectors.toList());
    }
}
