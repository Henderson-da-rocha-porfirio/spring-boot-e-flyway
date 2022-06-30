package com.tuyo.tuyofood.api.controller;

import com.tuyo.tuyofood.domain.entity.State;
import com.tuyo.tuyofood.domain.exception.EntidadeEmUsoException;
import com.tuyo.tuyofood.domain.exception.EntidadeNaoEncontradaException;
import com.tuyo.tuyofood.domain.repository.StateRepository;
import com.tuyo.tuyofood.domain.service.StateRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateRegisterService stateRegisterService;

    @GetMapping
    public List<State> listar() {
        return stateRepository.findAll();
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> buscar(@PathVariable Long stateId) {
        State state = stateRepository.findById(stateId).orElse(null);

        if (state != null) {
            return ResponseEntity.ok(state);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State adicionar(@RequestBody State state) {
        return stateRegisterService.salvar(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<State> atualizar(@PathVariable Long stateId,
                                            @RequestBody State state) {
        State stateAtual = stateRepository.findById(stateId).orElse(null);

        if (stateAtual != null) {
            BeanUtils.copyProperties(state, stateAtual, "id");

            stateAtual = stateRegisterService.salvar(stateAtual);
            return ResponseEntity.ok(stateAtual);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<?> remover(@PathVariable Long stateId) {
        try {
            stateRegisterService.excluir(stateId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }
}
