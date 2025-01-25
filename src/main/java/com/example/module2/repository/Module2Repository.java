package com.example.module2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Module2Repository extends JpaRepository<Module2Entity, Long> {
}
