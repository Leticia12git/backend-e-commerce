package edu.e_commerce.controller;

import edu.e_commerce.dtos.request.ProductRequest;
import edu.e_commerce.dtos.response.ProductResponse;
import edu.e_commerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ProductResponse> register(@RequestBody ProductRequest productRequest) {
        ProductResponse saved = productService.registerProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
