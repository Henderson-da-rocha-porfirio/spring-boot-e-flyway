package com.tuyo.tuyofood.jpa;

import com.tuyo.tuyofood.TuyoFoodApplication;
import com.tuyo.tuyofood.domain.entity.Kitchen;
import com.tuyo.tuyofood.domain.repository.KitchenRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

/* 1. Esta classe representa uma Main apenas para testar sem uma Controller.
*  2. Serve também para testar a inicialização não-web
*  3. ApplicationContext: é uma interface que gerencia o contexto da aplicação.
*  4. NONE = determina que aqui não é uma aplicação WEB. */

public class KitchenInclusionMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(TuyoFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRepository kitchenRepository = applicationContext.getBean(KitchenRepository.class);

        Kitchen kitchen1 = new Kitchen();
        kitchen1.setNome("Brasileira");

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setNome("Japonesa");

        kitchen1 = kitchenRepository.salvar(kitchen1);
        kitchen2 = kitchenRepository.salvar(kitchen2);

        System.out.printf("%d - %s\n", kitchen1.getId(), kitchen1.getNome());
        System.out.printf("%d - %s\n", kitchen2.getId(), kitchen2.getNome());
    }
}
