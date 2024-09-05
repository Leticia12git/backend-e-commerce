package edu.e_commerce.service;

import edu.e_commerce.converter.ProductConverter;
import edu.e_commerce.dtos.request.ProductRequest;
import edu.e_commerce.dtos.request.ProductUpdateRequest;
import edu.e_commerce.dtos.response.ProductResponse;
import edu.e_commerce.model.Product;
import edu.e_commerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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


    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Metodo responsavel por buscar um produto por id
     *
     * @param id
     * @return produto
     */

    public ProductResponse findById(Long id) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(() -> new Exception("Product not found"));;
        return productConverter.convertEntityToDTO(product);

    }

    /**
     * Metodo responsavel por cadastrar um produto
     *
     * @param productRequest
     * @return Produto
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

    /**
     * Metodo responsave poor atualizar um produto
     *
     * @param productRequest
     * @param id
     * @return Produto
     * @throws IOException
     */

    public ProductResponse updateProduct(ProductUpdateRequest productRequest, Long id) throws Exception {
        Product newProduct = productRepository.findById(id)
                .orElseThrow(() -> new Exception("Product not found"));
        newProduct.setPrice(productRequest.getPrice());
        newProduct.setQuantity(productRequest.getQuantity());
        Product savedProduct = productRepository.save(newProduct);
        return productConverter.convertEntityToDTO(savedProduct);
    }

    /**
     * Metodo responsavel por deletar um produto
     *
     * @param id
     */
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            productRepository.deleteById(id);
        }
    }
}