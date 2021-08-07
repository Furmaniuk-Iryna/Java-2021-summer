package com.example.final_project.repository;

import com.example.final_project.entity.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
    @Override
    List<Direction> findAll();
}
