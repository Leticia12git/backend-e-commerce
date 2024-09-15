package edu.e_commerce.converter;

import edu.e_commerce.dtos.response.ProductResponse;
import edu.e_commerce.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    @Autowired
    private ModelMapper modelMapper;

    public ProductResponse convertEntityToDTO(Product product) {
        return modelMapper.map(product, ProductResponse.class);
    }
}