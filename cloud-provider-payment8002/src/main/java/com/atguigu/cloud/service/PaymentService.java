package com.atguigu.cloud.service;

import com.atguigu.cloud.entities.Payment;
import org.springframework.stereotype.Service;


public interface PaymentService {
    int create(Payment payment);

    Payment getPaymentByID(Long id);
}
