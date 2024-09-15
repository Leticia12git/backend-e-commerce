package edu.e_commerce.dtos.response;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClientResponse {

    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "birthDate")
    private String birthDate;

    @Column(name = "cellphone")
    private String cellphone;

    @Column(name = "address")
    private String address;

    @Column(name = "cpf")
    private String cpf;
}
