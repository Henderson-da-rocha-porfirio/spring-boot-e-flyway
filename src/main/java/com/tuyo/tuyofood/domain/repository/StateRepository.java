package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.State;

import java.util.List;

public interface StateRepository {

    List<State> listar();
    State buscar(Long id);
    State salvar(State state);
    void remover(Long id);
}
