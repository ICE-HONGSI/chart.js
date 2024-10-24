package com.example.demo.bms.repository;

import com.example.demo.bms.entity.Sys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRepository extends JpaRepository<Sys, Long> {
}