package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.PaymentForm;
import com.tuyo.tuyofood.domain.entity.State;

import java.util.List;

public interface PaymentFormRepository {

    List<PaymentForm> listar();
    PaymentForm buscar(Long id);
    PaymentForm salvar(PaymentForm paymentForm);
    void remover(PaymentForm paymentForm);
}
