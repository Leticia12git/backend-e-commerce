package edu.e_commerce.controller;

import edu.e_commerce.dtos.request.ProductRequest;
import edu.e_commerce.dtos.request.ProductUpdateRequest;
import edu.e_commerce.dtos.response.ProductResponse;
import edu.e_commerce.model.Product;
import edu.e_commerce.service.ProductService;
import edu.e_commerce.service.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public  ResponseEntity<Page<Product>> findAll( @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productService.findAll(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Endpoint para buscar um produto por id
     *
     * @param id
     * @return produto
     */

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable("id") Long id) throws Exception {
        ProductResponse product = productService.findById(id);
        return ResponseEntity.ok(product);
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

    /**
     * Endpoint responsavel por atualizar um produto
     *
     * @param id
     * @return produto
     */

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id,
                                                         @RequestParam("quantity") int quantity,
                                                         @RequestParam("price") double price
                                                        ) throws Exception {
        ProductUpdateRequest productRequest = new ProductUpdateRequest( quantity, price);
        ProductResponse updatedProduct = productService.updateProduct(productRequest, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    /**
     * Endpoint responsavel por deletar um produto
     *
     * @param id
     * @return
     */

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}