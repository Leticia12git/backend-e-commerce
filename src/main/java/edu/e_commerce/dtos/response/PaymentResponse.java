package edu.e_commerce.dtos.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PaymentResponse {

    @Column(name = "paymentMethod")
    private String paymentMethod;
}
