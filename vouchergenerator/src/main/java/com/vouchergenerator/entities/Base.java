package com.vouchergenerator.entities;

import javax.persistence.*;

@MappedSuperclass
public abstract class Base {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
