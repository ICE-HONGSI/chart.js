package com.example.demo.bms.repository;

import com.example.demo.bms.entity.Bms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BmsRepository extends JpaRepository<Bms, Long> {
}
