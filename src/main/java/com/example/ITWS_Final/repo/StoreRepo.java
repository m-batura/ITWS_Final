package com.example.ITWS_Final.repo;


import com.example.ITWS_Final.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StoreRepo extends JpaRepository<Store, Long> {
    List<Store> findAllByDeletedAtIsNull();

    Optional<Store> findByIdAndDeletedAtIsNull(Integer id);

    Boolean existsByAddressAndDeletedAtIsNull(String address);

    Boolean existsByIdAndDeletedAtIsNull(Integer id);
}
