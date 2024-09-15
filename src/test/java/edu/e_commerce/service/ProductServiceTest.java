package edu.e_commerce.service;

import edu.e_commerce.converter.ProductConverter;
import edu.e_commerce.dtos.request.ProductRequest;
import edu.e_commerce.dtos.response.ProductResponse;
import edu.e_commerce.model.Product;
import edu.e_commerce.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductConverter productConverter;

    @Mock
    private UploadImage uploadImage;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName(value = "Sucesse create product")
    public void registerProduct() throws Exception {
        // Arrange
        MultipartFile mockImage = mock(MultipartFile.class);
        when(mockImage.getOriginalFilename()).thenReturn("test-image.png");
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Product Name");
        productRequest.setPrice(100.0);
        productRequest.setImage(mockImage);
        productRequest.setQuantity(10);

        Product savedProduct = new Product();
        savedProduct.setId(1L);
        savedProduct.setName("Product Name");
        savedProduct.setPrice(100.0);
        savedProduct.setImage("");
        savedProduct.setQuantity(10);

        ProductResponse expectedResponse = new ProductResponse();
        expectedResponse.setName("Product Name");
        expectedResponse.setPrice(100.0);
        expectedResponse.setImage("");
        expectedResponse.setQuantity(10);

        when(uploadImage.saveImage(any(MultipartFile.class))).thenReturn("");
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);
        when(productConverter.convertEntityToDTO(savedProduct)).thenReturn(expectedResponse);

        ProductResponse actualResponse = productService.registerProduct(productRequest);

        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(uploadImage, times(1)).saveImage(any(MultipartFile.class));
        verify(productRepository, times(1)).save(any(Product.class));
        verify(productConverter, times(1)).convertEntityToDTO(savedProduct);
    }


    @Test
    @DisplayName(value = "Sucess findAll products")
    void findAll() {

    }

    @Test
    @DisplayName(value = "Sucess find by Id  products")
    void findById() {
    }

    @Test
    @DisplayName(value = "Sucess update products")
    void updateProduct(ProductRequest productRequest , Long id) {

        ProductRequest product = new ProductRequest();

    }

    @Test
    @DisplayName(value = "Sucess delete  products")
    void deleteProduct(Long id) {

    }
}
