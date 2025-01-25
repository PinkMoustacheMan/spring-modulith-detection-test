package com.example.module3.repository;

import com.example.common.repository.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Module3Entity extends BaseEntity<Module3Entity> {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    protected boolean equalsId(Module3Entity other) {
        return this.id != null && Objects.equals(this.getId(), other.getId());
    }
}
