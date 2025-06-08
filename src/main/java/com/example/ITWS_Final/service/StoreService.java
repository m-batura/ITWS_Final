package com.example.ITWS_Final.service;

import com.example.ITWS_Final.entity.Store;
import com.example.ITWS_Final.repo.StoreRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepo repository;

    public List<Store> getStores() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Store> getStoreById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public void createStore(Store model) {
        existsByName(model);
        Store store = new Store();
        store.setAddress(model.getAddress());
        store.setWorkingHours(model.getWorkingHours());
        store.setCreatedAt(LocalDateTime.now());
        repository.save(store);
    }

    public void updateStore(Integer id, Store model) {
        Store store = getStoreById(id).orElseThrow();
        if (!store.getAddress().equals(model.getAddress())) {
            existsByName(model);
        }
        store.setAddress(model.getAddress());
        store.setWorkingHours(model.getWorkingHours());
        store.setUpdatedAt(LocalDateTime.now());
        repository.save(store);
    }

    public void deleteStoreById(Integer id) {
        Store store = getStoreById(id).orElseThrow();
        store.setDeletedAt(LocalDateTime.now());
        repository.save(store);
    }

    public Boolean existsById(Integer id) {
        return repository.existsByIdAndDeletedAtIsNull(id);
    }

    private void existsByName(Store model) {

        if (repository.existsByAddressAndDeletedAtIsNull(model.getAddress()))
            throw new RuntimeException("EXISTS_BY_ADDRESS");
    }
}
