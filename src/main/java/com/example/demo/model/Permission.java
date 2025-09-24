package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    public Permission() {}
    public Permission(String name) { this.name = name; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Permission that)) return false;
        return Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() { return Objects.hash(name); }
}