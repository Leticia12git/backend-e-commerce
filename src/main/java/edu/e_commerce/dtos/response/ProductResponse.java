package edu.e_commerce.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {


    private String name;
    private int quantity;
    private Double price;
    private String image;
}