package com.tuyo.tuyofood.domain.service;

import com.tuyo.tuyofood.domain.entity.City;
import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.entity.State;
import com.tuyo.tuyofood.domain.exception.EntidadeEmUsoException;
import com.tuyo.tuyofood.domain.exception.EntidadeNaoEncontradaException;
import com.tuyo.tuyofood.domain.repository.CityRepository;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import com.tuyo.tuyofood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityRegisterService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    public City salvar(City city) {
        Long stateId = city.getState().getId();
        State state = stateRepository.buscar(stateId);

        if (state == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe cadastro de state com código %d", stateId));
        }

        city.setState(state);

        return cityRepository.salvar(city);
    }

    public void excluir(Long cityId) {
        try {
            cityRepository.remover(cityId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cityId com código %d", cityId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("City de código %d não pode ser removida, pois está em uso", cityId));
        }
    }
}
