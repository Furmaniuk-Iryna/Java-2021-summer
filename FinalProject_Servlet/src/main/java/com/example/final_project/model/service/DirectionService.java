package com.example.final_project.model.service;

import com.example.final_project.model.dao.DaoFactory;
import com.example.final_project.model.dao.DirectionDao;
import com.example.final_project.model.dao.TariffDao;
import com.example.final_project.model.entity.Direction;
import com.example.final_project.model.entity.Tariff;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
/**
 * DirectionServise is a service we'll be using to form response and
 * where there is all the business logic for the essence Direction
 */
public class DirectionService {
    DaoFactory daoFactory = DaoFactory.getInstance();
    private static Logger log = Logger.getLogger(String.valueOf(DirectionService.class));

    public List<Direction> getAllDirections(){
        log.info("Service: started getAllDirections");
        try (DirectionDao dao = daoFactory.createDirectionDao()) {
            return Optional.ofNullable(dao.findAll()).orElseThrow(RuntimeException::new);
        }
    }

    public Direction getDirectionById(long id){
        log.info("Service: started getDirectionById");
        try (DirectionDao dao = daoFactory.createDirectionDao()) {
            return Optional.ofNullable(dao.findById(id)).orElseThrow(RuntimeException::new);
        }
    }

    public List<Direction> sortedDirectionsForUkLocale() {
        log.info("Service: started sortedDirectionsForUkLocale");
        try (DirectionDao dao = daoFactory.createDirectionDao()) {
            return dao.findAll().stream()
                    .sorted(Comparator.comparing(Direction::getCityUk)).collect(Collectors.toList());
        }}

    public List<Direction> sortedDirectionsForEnLocale() {
        log.info("Service: started sortedDirectionsForEnLocale");
        try (DirectionDao dao = daoFactory.createDirectionDao()) {
        return dao.findAll().stream()
                .sorted(Comparator.comparing(Direction::getCityEn)).collect(Collectors.toList());
    }}

    public Direction getNeededDirection(String city) {
        log.info("Service: started getNeededDirection");
        try (DirectionDao dao = daoFactory.createDirectionDao()) {
            return dao.findAll().stream()
                    .filter(direction -> (direction.getCityEn().toLowerCase().equals(city.toLowerCase())
                            || direction.getCityUk().toLowerCase().equals(city.toLowerCase())))
                    .findFirst().orElseThrow(() -> new RuntimeException("Incorrect city"));
        }}

}
