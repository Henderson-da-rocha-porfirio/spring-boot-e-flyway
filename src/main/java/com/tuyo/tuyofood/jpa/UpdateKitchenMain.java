package com.tuyo.tuyofood.jpa;

import com.tuyo.tuyofood.TuyoFoodApplication;
import com.tuyo.tuyofood.domain.entity.Kitchen;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/* 1. Esta classe representa uma Main apenas para testar sem uma Controller.
*  2. Serve também para testar a inicialização não-web
*  3. ApplicationContext: é uma interface que gerencia o contexto da aplicação.
*  4. NONE = determina que aqui não é uma aplicação WEB. */

public class UpdateKitchenMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(TuyoFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KitchenRegister kitchenRegister = applicationContext.getBean(KitchenRegister.class);

        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);
        kitchen.setNome("Chinese");

        kitchenRegister.salvar(kitchen);
    }
}
