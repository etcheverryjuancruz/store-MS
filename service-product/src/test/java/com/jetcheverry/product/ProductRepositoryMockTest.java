package com.jetcheverry.product;


import com.jetcheverry.product.entity.Category;
import com.jetcheverry.product.entity.Product;
import com.jetcheverry.product.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class ProductRepositoryMockTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenFindByCategoryThenReturnProductList(){
        Category category = Category.builder().id(1L).build();

        List<Product> founds = productRepository.findByCategory(category);
        Assertions.assertEquals(founds.size(), 2);
    }
}
