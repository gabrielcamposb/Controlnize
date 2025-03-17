package com.Controlnize.Controlnize;

import com.Controlnize.Controlnize.model.Product;
import com.Controlnize.Controlnize.repository.ProductRepository;
import com.Controlnize.Controlnize.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listAllProducts() {
        Product product1 = new Product();
        product1.setName("Product A");
        product1.setAmount(10);
        product1.setPrice(29.99);

        Product product2 = new Product();
        product2.setName("Product B");
        product2.setAmount(5);
        product2.setPrice(19.99);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));
        List<Product> products = productService.listAllProducts();

        assertEquals(2, products.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void findProduct() {
        Product product = new Product();
        product.setName("Product A");
        product.setAmount(10);
        product.setPrice(29.99);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product found = productService.findProduct(1L);

        assertNotNull(found);
        assertEquals("Product A", found.getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void saveProduct() {
        Product product = new Product();
        product.setName("Product A");
        product.setAmount(10);
        product.setPrice(29.99);

        when(productRepository.save(product)).thenReturn(product);
        Product saved = productService.saveProduct(product);

        assertNotNull(saved);
        assertEquals("Product A", saved.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void deleteProduct() {
        doNothing().when(productRepository).deleteById(1L);

        productService.deleteProduct(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}