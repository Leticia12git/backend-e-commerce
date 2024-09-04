package edu.e_commerce.controller;

import edu.e_commerce.dtos.request.ProductRequest;
import edu.e_commerce.dtos.response.ProductResponse;
import edu.e_commerce.model.Product;
import edu.e_commerce.service.ProductService;
import edu.e_commerce.service.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Classe de serviço responsavel pelas regras de negocios
 */

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UploadImage uploadImage;

    /**
     * Metodo responsavel por listar todos os produtos
     *
     * @return List<Products></Products>
     */

    @GetMapping("/all")
    public List<Product> findAll() {
        return productService.findAll();
    }

    /**
     * Metodo respnsavel por cadastrar um produto
     *
     * @param name
     * @param quantity
     * @param price
     * @param image
     * @return Product
     */

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<ProductResponse> createProduct(@RequestParam("name") String name,
                                                         @RequestParam("quantity") int quantity,
                                                         @RequestParam("price") double price,
                                                         @RequestParam("image") MultipartFile image) {
        try {
            ProductRequest productRequest = new ProductRequest(name, image, quantity, price);
            ProductResponse save = productService.registerProduct(productRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(save);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}