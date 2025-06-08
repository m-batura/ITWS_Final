package com.example.ITWS_Final.controller;

import com.example.ITWS_Final.entity.Store;
import com.example.ITWS_Final.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/store")
@CrossOrigin
@RequiredArgsConstructor
public class StoreController {
    private final StoreService service;

    @GetMapping
    public List<Store> getStores() {
        return service.getStores();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getStoreById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createStore(@RequestBody Store store) {
        service.createStore(store);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStore(@PathVariable Integer id, @RequestBody Store store) {
        service.updateStore(id, store);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStore(@PathVariable Integer id) {
        service.deleteStoreById(id);
    }
}
