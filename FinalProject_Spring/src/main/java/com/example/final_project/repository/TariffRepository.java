package com.example.final_project.repository;

import com.example.final_project.entity.Tariff;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TariffRepository extends JpaRepository<Tariff,Long> {
    Tariff findTariffById(Long id);

}
