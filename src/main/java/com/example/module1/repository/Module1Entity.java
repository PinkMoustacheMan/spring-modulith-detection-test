package com.example.module1.repository;

import com.example.common.repository.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Module1Entity extends BaseEntity<Module1Entity> {

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
    protected boolean equalsId(Module1Entity other) {
        return this.id != null && Objects.equals(this.getId(), other.getId());
    }
}
