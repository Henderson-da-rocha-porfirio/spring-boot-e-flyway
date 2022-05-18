package com.tuyo.tuyofood.jpa;

import com.tuyo.tuyofood.TuyoFoodApplication;
import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

/* 1. Esta classe representa uma Main apenas para testar sem uma Controller.
*  2. Serve também para testar a inicialização não-web
*  3. ApplicationContext: é uma interface que gerencia o contexto da aplicação.
*  4. NONE = determina que aqui não é uma aplicação WEB.
*  5. printf:
*  a. Tipo %s: 's' de 'string' = imprime o nome do restaurant.
*  b. Tipo %f: 'f' de float = taxa de frete.
*  c. Tipo %s: 's' de 'string' = imprime o nome da kitchen. */

public class RestaurantQueryMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(TuyoFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        RestaurantRepository restaurantRepository = applicationContext.getBean(RestaurantRepository.class);

        List<Restaurant> allRestaurants = restaurantRepository.listar();

        for (Restaurant restaurant : allRestaurants) {
            System.out.printf("%s - %f - %s\n", restaurant.getNome(),
                    restaurant.getTaxaFrete(), restaurant.getKitchen().getNome());
        }
    }
}
