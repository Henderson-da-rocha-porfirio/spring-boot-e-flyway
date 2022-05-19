package com.tuyo.tuyofood.jpa;

import com.tuyo.tuyofood.TuyoFoodApplication;
import com.tuyo.tuyofood.domain.entity.City;
import com.tuyo.tuyofood.domain.entity.Restaurant;
import com.tuyo.tuyofood.domain.repository.CityRepository;
import com.tuyo.tuyofood.domain.repository.RestaurantRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class CityQueryMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(TuyoFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        CityRepository cityRepository = applicationContext.getBean(CityRepository.class);

        List<City> AllCities = cityRepository.listar();

        for (City city : AllCities) {
            System.out.printf("%s - %s\n", city.getNome(), city.getState().getNome());
        }
    }
}
