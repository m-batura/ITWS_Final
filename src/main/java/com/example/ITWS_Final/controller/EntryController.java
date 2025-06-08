package com.example.ITWS_Final.controller;


import com.example.ITWS_Final.entity.Entry;
import com.example.ITWS_Final.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/entry")
@CrossOrigin
@RequiredArgsConstructor
public class EntryController {
    private final EntryService service;

    @GetMapping
    public List<Entry> getEntrys() {
        return service.getEntrys();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable Integer id) {
        return ResponseEntity.of(service.getEntryById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createEntry(@RequestBody Entry entry) {
        service.createEntry(entry);
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEntry(@PathVariable Integer id, @RequestBody Entry entry) {
        service.updateEntry(id, entry);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEntry(@PathVariable Integer id) {
        service.deleteEntryById(id);
    }
}
