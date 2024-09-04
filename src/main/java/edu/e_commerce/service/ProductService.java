package edu.e_commerce.service;

import edu.e_commerce.converter.ProductConverter;
import edu.e_commerce.dtos.request.ProductRequest;
import edu.e_commerce.dtos.response.ProductResponse;
import edu.e_commerce.model.Product;
import edu.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe responsavel pela regra de negocio de produtoss
 */

@Service
public class ProductService {

    @Autowired
    private UploadImage uploadImage;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    /**
     * Metodo responsavel por listar todos os produtos
     *
     * @return List<Products></Products>
     */

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    /**
     * Metodo responsavel por cadastrar um produto
     *
     * @param productRequest
     * @return
     * @throws Exception
     */

    public ProductResponse registerProduct(ProductRequest productRequest) throws Exception {
        Product newProduct = new Product();
        newProduct.setName(productRequest.getName());
        newProduct.setPrice(productRequest.getPrice());
        newProduct.setImage(uploadImage.saveImage(productRequest.getImage()));
        newProduct.setQuantity(productRequest.getQuantity());
        Product savedProduct = productRepository.save(newProduct);
        return productConverter.convertEntityToDTO(savedProduct);
    }
}