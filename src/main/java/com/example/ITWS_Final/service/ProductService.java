package com.example.ITWS_Final.service;

import com.example.ITWS_Final.entity.Product;
import com.example.ITWS_Final.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo repository;

    public List<Product> getProducts() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Product> getProductById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public void createProduct(Product model) {
        existsByName(model);
        Product product = new Product();
        product.setName(model.getName());
        product.setWeight(model.getWeight());
        product.setPrice(model.getPrice());
        product.setCreatedAt(LocalDateTime.now());
        repository.save(product);
    }

    public void updateProduct(Integer id, Product model) {
        Product product = getProductById(id).orElseThrow();
        if (!product.getName().equals(model.getName())) {
            existsByName(model);
        }
        product.setName(model.getName());
        product.setWeight(model.getWeight());
        product.setPrice(model.getPrice());
        product.setUpdatedAt(LocalDateTime.now());
        repository.save(product);
    }

    public void deleteProductById(Integer id) {
        Product product = getProductById(id).orElseThrow();
        product.setDeletedAt(LocalDateTime.now());
        repository.save(product);
    }

    public Boolean existsById(Integer id) {
        return repository.existsByIdAndDeletedAtIsNull(id);
    }

    private void existsByName(Product model) {

        if (repository.existsByNameAndDeletedAtIsNull(model.getName()))
            throw new RuntimeException("EXISTS_BY_ADDRESS");
    }
}
