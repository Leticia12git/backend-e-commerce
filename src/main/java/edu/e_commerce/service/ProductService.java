package edu.e_commerce.service;

import edu.e_commerce.converter.ProductConverter;
import edu.e_commerce.dtos.request.ProductRequest;
import edu.e_commerce.dtos.response.ProductResponse;
import edu.e_commerce.model.Product;
import edu.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;


    public ProductResponse registerProduct(ProductRequest productRequest){
        Product newProduct = new Product();
        newProduct.setName(productRequest.name());
        newProduct.setPrice(productRequest.price());
        newProduct.setQuantity(productRequest.quantity());
        Product savedProduct = productRepository.save(newProduct);
        return productConverter.convertEntityToDTO(savedProduct);
    }
}