package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<EndpointPermission> permissions;

    public Role() {}
    public Role(String name, Set<EndpointPermission> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Set<EndpointPermission> getPermissions() { return permissions; }
    public void setPermissions(Set<EndpointPermission> permissions) { this.permissions = permissions; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role that)) return false;
        return Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() { return Objects.hash(name); }
}