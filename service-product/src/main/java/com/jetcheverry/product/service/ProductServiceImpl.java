package com.jetcheverry.product.service;

import com.jetcheverry.product.entity.Category;
import com.jetcheverry.product.entity.Product;
import com.jetcheverry.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> listAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(Product product) {
        product.setStatus("CREATED");
        product.setCreateAt(new Date());
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        Optional<Product> productDBOpt = productRepository.findById(product.getId());
        return productDBOpt.map((productValue) -> {
            productValue.setName(product.getName());
            productValue.setDescription(product.getDescription());
            productValue.setCategory(product.getCategory());
            productValue.setPrice(product.getPrice());
            productValue.setStock(productValue.getStock());
            return productRepository.save(productValue);
        }).orElse(null);

    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> productDBOpt = productRepository.findById(id);
        return productDBOpt.map((productValue) -> {
            productValue.setStatus("DELETED");
            return productRepository.save(productValue);
        }).orElse(null);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public Product updateStock(Long id, Double quantity) {
        Optional<Product> productDBOpt = productRepository.findById(id);

        return productDBOpt.map((productValue) -> {
            productValue.setStock(productValue.getStock() + quantity);
            return productRepository.save(productValue);
        }).orElse(null);
    }
}
