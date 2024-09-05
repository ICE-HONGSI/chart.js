package com.example.demo.repository;

import com.example.demo.entity.BodyPartsData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyPartsDataRepository extends JpaRepository<BodyPartsData, Long> {
}
