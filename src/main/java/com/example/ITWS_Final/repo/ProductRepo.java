package com.example.ITWS_Final.repo;

import com.example.ITWS_Final.entity.Product;
import com.example.ITWS_Final.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findAllByDeletedAtIsNull();

    Optional<Product> findByIdAndDeletedAtIsNull(Integer id);

    Boolean existsByNameAndDeletedAtIsNull(String name);

    Boolean existsByIdAndDeletedAtIsNull(Integer id);
}
