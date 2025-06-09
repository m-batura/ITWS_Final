package com.example.ITWS_Final.service;

import com.example.ITWS_Final.entity.Entry;
import com.example.ITWS_Final.entity.Product;
import com.example.ITWS_Final.entity.Store;
import com.example.ITWS_Final.repo.EntryRepo;
import com.example.ITWS_Final.repo.ProductRepo;
import com.example.ITWS_Final.repo.StoreRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntryService {
    private final EntryRepo repository;
    private final StoreRepo storeRepo;
    private final ProductRepo productRepo;


    public List<Entry> getEntries() {
        return repository.findAllByDeletedAtIsNull();
    }

    public Optional<Entry> getEntryById(Integer id) {
        return repository.findByIdAndDeletedAtIsNull(id);
    }

    public void createEntry(Entry model) {
//        Store store = storeRepo.findById(model.getStore().getId())
//                .orElseThrow(() -> new EntityNotFoundException("Store not found"));
//        Product product = productRepo.findById(model.getProduct().getId())
//                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        Entry entry = new Entry();
        Store store = storeRepo.findById(model.getStoreId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));
        entry.setStore(store);
        Product product = productRepo.findById(model.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Store not found"));
        entry.setProduct(product);
        entry.setQuantity(model.getQuantity());
        entry.setCreatedAt(LocalDateTime.now());
        repository.save(entry);
    }

    public void updateEntry(Integer id, Entry model) {
        Entry entry = getEntryById(id).orElseThrow();
        entry.setStore(model.getStore());
        entry.setProduct(model.getProduct());
        entry.setQuantity(model.getQuantity());
        entry.setUpdatedAt(LocalDateTime.now());
        repository.save(entry);
    }

    public void deleteEntryById(Integer id) {
        Entry entry = getEntryById(id).orElseThrow();
        entry.setDeletedAt(LocalDateTime.now());
        repository.save(entry);
    }

    public Boolean existsById(Integer id) {
        return repository.existsByIdAndDeletedAtIsNull(id);
    }
}
