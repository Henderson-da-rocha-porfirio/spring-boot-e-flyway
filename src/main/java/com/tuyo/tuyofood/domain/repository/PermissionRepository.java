package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.Permission;
import com.tuyo.tuyofood.domain.entity.State;

import java.util.List;

public interface PermissionRepository {

    List<Permission> listar();
    Permission buscar(Long id);
    Permission salvar(Permission estado);
    void remover(Permission estado);
}
