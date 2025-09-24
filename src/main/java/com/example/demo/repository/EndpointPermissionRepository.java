package com.example.demo.repository;

import com.example.demo.model.EndpointPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EndpointPermissionRepository extends JpaRepository<EndpointPermission, Long> {
    EndpointPermission findByName(String name);
}