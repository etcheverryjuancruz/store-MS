package com.jetcheverry.product;

import com.jetcheverry.product.entity.Category;
import com.jetcheverry.product.entity.Product;
import com.jetcheverry.product.repository.ProductRepository;
import com.jetcheverry.product.service.ProductService;
import com.jetcheverry.product.service.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class ProductServiceMockTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;


    @BeforeEach
    public void mockProduct() {
        Product product = Product.builder()
                .id(1L)
                .name("computer")
                .category(Category.builder().id(1L).build())
                .price(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5")).build();
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        lenient().when(productRepository.save(product)).thenReturn(product);
    }




    @Test
    public void whenGetIDProductThenReturnProduct(){

        Product p = productService.getProduct(1L);

        Assertions.assertEquals(p.getName(), "computer");

    }

    @Test
    public void whenUpdateStock_ThenReturnNewStock(){
        Product p = productService.updateStock(1L, Double.parseDouble("8"));

        Assertions.assertEquals(p.getStock(), 13);

    }

}
