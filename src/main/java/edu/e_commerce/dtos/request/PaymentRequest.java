package edu.e_commerce.dtos.request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PaymentRequest {

    @Column(name = "paymentMethod")
    private String paymentMethod;
}
