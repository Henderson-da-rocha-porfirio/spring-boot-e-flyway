package com.tuyo.tuyofood.jpa;

import com.tuyo.tuyofood.TuyoFoodApplication;
import com.tuyo.tuyofood.domain.entity.City;
import com.tuyo.tuyofood.domain.entity.PaymentForm;
import com.tuyo.tuyofood.domain.repository.CityRepository;
import com.tuyo.tuyofood.domain.repository.PaymentFormRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class PaymentFormQueryMain {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new SpringApplicationBuilder(TuyoFoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentFormRepository paymentFormRepository = applicationContext.getBean(PaymentFormRepository.class);

        List<PaymentForm> allPaymentForms = paymentFormRepository.listar();

        for (PaymentForm paymentForm : allPaymentForms) {
            System.out.println(paymentForm.getDescricao());
        }
    }
}
