package com.tuyo.tuyofood.domain.repository;

import com.tuyo.tuyofood.domain.entity.PaymentForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentFormRepository extends JpaRepository<PaymentForm, Long> {

}
