package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class EndpointPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String httpMethod;
    private String endpoint;

    public EndpointPermission() {}
    public EndpointPermission(String name, String httpMethod, String endpoint) {
        this.name = name;
        this.httpMethod = httpMethod;
        this.endpoint = endpoint;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getHttpMethod() { return httpMethod; }
    public void setHttpMethod(String httpMethod) { this.httpMethod = httpMethod; }
    public String getEndpoint() { return endpoint; }
    public void setEndpoint(String endpoint) { this.endpoint = endpoint; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndpointPermission that)) return false;
        return Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() { return Objects.hash(name); }
}