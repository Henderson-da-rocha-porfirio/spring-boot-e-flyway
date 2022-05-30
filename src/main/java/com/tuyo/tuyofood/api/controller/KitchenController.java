package com.tuyo.tuyofood.api.controller;

import com.tuyo.tuyofood.api.model.KitchensXmlWrapper;
import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
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
 *  5. BeanUtils.copyProperties: faz com que os valores das propriedades de Kitchen sejam passados para KitchenAtual. Isso economiza código sem precisar jogar get ou set */

@RestController
@RequestMapping(value = "/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

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
        return kitchenRepository.salvar(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> atualizar(@PathVariable Long kitchenId,
                                             @RequestBody Kitchen kitchen) {
        Kitchen kitchenAtual = kitchenRepository.buscar(kitchenId);

        if (kitchenAtual != null) {
//			kitchenAtual.setNome(kitchen.getNome());
            BeanUtils.copyProperties(kitchen, kitchenAtual, "id");

            kitchenAtual = kitchenRepository.salvar(kitchenAtual);
            return ResponseEntity.ok(kitchenAtual);
        }

        return ResponseEntity.notFound().build();
    }
}
