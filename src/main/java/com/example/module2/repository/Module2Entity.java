package com.example.module2.repository;

import com.example.common.repository.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Module2Entity extends BaseEntity<Module2Entity> {

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
    protected boolean equalsId(Module2Entity other) {
        return this.id != null && Objects.equals(this.getId(), other.getId());
    }
}
