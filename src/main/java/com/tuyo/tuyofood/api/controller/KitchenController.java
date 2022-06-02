package com.tuyo.tuyofood.api.controller;

import com.tuyo.tuyofood.api.model.KitchensXmlWrapper;
import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.exception.EntidadeEmUsoException;
import com.tuyo.tuyofood.domain.exception.EntidadeNaoEncontradaException;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import com.tuyo.tuyofood.domain.service.KitchenRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* 1. @PostMapping: Mapeamento do método POST
 *  2. @RequestBody: Vinculação do corpo da requisição com o objeto em questão "Kitchen" (instanciar e fazer o Bind)
 *  3. @ResponseStatus: customizando o status
 *  4. @PathVariable: Faz o Bind
 *  5. BeanUtils.copyProperties: faz com que os valores das propriedades de Kitchen sejam passados para KitchenAtual. Isso economiza código sem precisar jogar get ou set
 *  6. KitchenController = é a controladora onde está sendo implementada a API
 *  7. KitchenController: agora o controlador tem acesso ao ResponseEntity e traduz EntidadeEmUsoException para um "CONFLICT"(409) HTTP. */

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenRegisterService kitchenRegisterService;

    /* Listando Coleções de Kitchen */
    @GetMapping
    public List<Kitchen> listar() {
        return kitchenRepository.listar();
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchensXmlWrapper listarXml() {
        return new KitchensXmlWrapper(kitchenRepository.listar());
    }

    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> buscar(@PathVariable Long kitchenId) {
        Kitchen kitchen = kitchenRepository.buscar(kitchenId);

        if (kitchen != null) {
            return ResponseEntity.ok(kitchen);
        }

//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen adicionar(@RequestBody Kitchen kitchen) {
        return kitchenRegisterService.salvar(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> atualizar(@PathVariable Long kitchenId,
                                             @RequestBody Kitchen kitchen) {
        Kitchen kitchenAtual = kitchenRepository.buscar(kitchenId);

        if (kitchenAtual != null) {
            BeanUtils.copyProperties(kitchen, kitchenAtual, "id");

            kitchenAtual = kitchenRepository.salvar(kitchenAtual);
            return ResponseEntity.ok(kitchenAtual);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> remover(@PathVariable Long kitchenId) {
        try {
            kitchenRegisterService.excluir(kitchenId);
            return ResponseEntity.noContent().build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();

        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
