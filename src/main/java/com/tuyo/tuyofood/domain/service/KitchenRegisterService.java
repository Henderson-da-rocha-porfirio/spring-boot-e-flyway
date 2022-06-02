package com.tuyo.tuyofood.domain.service;

import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

/* 1. @Service = Também é um @Component
*  2. Uma classe de Serviço não deve retornar um ResponseEntity porque essa classe de serviço é negócio.
* Ele deve ficar na classe controladora KitchenController.
*  3. E o tratamento de exceções deve ser feito aqui e não mais em KitchenController.
*  4. Esta classe não pode ter acesso aquelas classes que estão implementando a API.
*  5. Sendo uma Classe de Serviço, ela não tem nenhum conhecimento nenhum de protocolo HTTP. */

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
