package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
