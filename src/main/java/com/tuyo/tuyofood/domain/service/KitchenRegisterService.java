package com.tuyo.tuyofood.domain.service;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/* @Service = Também é um @Component */

@Service
public class KitchenRegisterService {

    @Autowired
    private KitchenRepository kitchenRepository;

    public Kitchen salvar(Kitchen kitchen) {
        return kitchenRepository.salvar(kitchen);
    }

    public void excluir(Long kitchenId) {
        try {
            kitchenRepository.remover(kitchenId);

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de kitchen com código %d", kitchenId));

        } catch (DataIntegrityViolationException e) {
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", kitchenId));
        }
    }
}
