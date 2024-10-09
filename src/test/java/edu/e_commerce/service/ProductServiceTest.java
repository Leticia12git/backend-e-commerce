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
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @DisplayName("")
    void testRegisterProduct_Success() throws Exception {
        ProductRequest request = new ProductRequest();
        Product product = new Product();
        when(uploadImage.saveImage(any(MultipartFile.class))).thenReturn("imagePath");
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(productConverter.convertEntityToDTO(any(Product.class))).thenReturn(new ProductResponse());

        ProductResponse response = productService.registerProduct(request);

        assertEquals("Product Name", response.getName());
        assertEquals(100.0, response.getPrice());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    @DisplayName("")
    void testRegisterProduct_ImageUploadFailure() throws Exception {
        ProductRequest request = new ProductRequest();
        when(uploadImage.saveImage(any(MultipartFile.class))).thenThrow(new Exception("Upload failed"));

        Exception exception = assertThrows(Exception.class, () -> {
            productService.registerProduct(request);
        });

        assertEquals("Upload failed", exception.getMessage());
        verify(productRepository, times(0)).save(any(Product.class));
    }


    @Test
    @DisplayName("")
    void testRegisterProduct_SaveFailure() throws Exception {
        ProductRequest request = new ProductRequest();
        when(uploadImage.saveImage(any(MultipartFile.class))).thenReturn("imagePath");
        when(productRepository.save(any(Product.class))).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            productService.registerProduct(request);
        });

        assertEquals("Database error", exception.getMessage());
        verify(uploadImage, times(1)).saveImage(any(MultipartFile.class));
        verify(productRepository, times(1)).save(any(Product.class));
    }


    @Test
    @DisplayName("")
    void testRegisterProduct_InvalidQuantity() throws Exception {
        ProductRequest request = new ProductRequest();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.registerProduct(request);
        });
        assertEquals("Quantity must be positive", exception.getMessage());
        verify(productRepository, times(0)).save(any(Product.class));
    }


    @Test
    @DisplayName("")
    void testRegisterProduct_NullImage() throws Exception {
        ProductRequest request = new ProductRequest( );
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.registerProduct(request);
        });
        assertEquals("Image is required", exception.getMessage());
        verify(productRepository, times(0)).save(any(Product.class));
    }
}