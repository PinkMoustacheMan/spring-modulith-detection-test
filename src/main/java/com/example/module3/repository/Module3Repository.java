package com.example.module3.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Module3Repository extends JpaRepository<Module3Entity, Long> {
}
