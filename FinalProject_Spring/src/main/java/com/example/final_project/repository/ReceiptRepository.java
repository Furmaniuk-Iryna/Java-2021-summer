package com.example.final_project.repository;

import com.example.final_project.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {

}
