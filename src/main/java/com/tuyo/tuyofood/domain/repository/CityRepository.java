package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.City;
import com.tuyo.tuyofood.domain.entity.State;

import java.util.List;

public interface CityRepository {

    List<City> listar();
    City buscar(Long id);
    City salvar(City estado);
    void remover(City estado);
}
