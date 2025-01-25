package com.example.module1.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Module1Repository extends JpaRepository<Module1Entity, Long> {
}
