package com.tuyo.tuyofood.domain.service;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.entity.State;
import com.tuyo.tuyofood.domain.exception.EntidadeEmUsoException;
import com.tuyo.tuyofood.domain.exception.EntidadeNaoEncontradaException;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import com.tuyo.tuyofood.domain.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class StateRegisterService {

    @Autowired
    private StateRepository stateRepository;

    public State salvar(State state) {
        return stateRepository.salvar(state);
    }

    public void excluir(Long stateId) {
        try {
            stateRepository.remover(stateId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de state com código %d", stateId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("State de código %d não pode ser removido, pois está em uso", stateId));
        }
    }
}
