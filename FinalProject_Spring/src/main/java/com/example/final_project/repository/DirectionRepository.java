package com.example.final_project.repository;

import com.example.final_project.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Query(value = "SELECT * FROM Direction d WHERE d.city_en LIKE CONCAT('%', ?1, '%') or d.city_uk LIKE CONCAT('%', ?1, '%') ",nativeQuery = true)
    List<Direction> findDirectionsLike(@Param("city") String city);
}
