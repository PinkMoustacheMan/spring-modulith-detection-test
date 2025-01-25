package com.example.module3.sub.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubModuleRepository extends JpaRepository<SubModuleEntity, Long> {
}
