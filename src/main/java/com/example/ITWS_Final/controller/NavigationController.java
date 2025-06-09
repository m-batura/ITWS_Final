package com.example.ITWS_Final.controller;

import com.example.ITWS_Final.entity.Entry;
import com.example.ITWS_Final.entity.Product;
import com.example.ITWS_Final.entity.Store;
import com.example.ITWS_Final.service.EntryService;
import com.example.ITWS_Final.service.ProductService;
import com.example.ITWS_Final.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class NavigationController {
    private final ProductService productService;
    private final StoreService storeService;
    private final EntryService entryService;


    @GetMapping("/")
    public String home() {
        return "redirect:/products";
    }

    @ModelAttribute("menu")
    public Map<String, String> populateNavbar() {
        Map<String, String> menu = new HashMap<>();
        menu.put("products", "Products");
        menu.put("entries", "Entries");
        menu.put("stores", "Stores");
        return menu;
    }

    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/stores")
    public String showStores(Model model){
        List<Store> stores = storeService.getStores();
        model.addAttribute("stores", stores);
        return "stores";
    }

    @GetMapping("/entries")
    public String showEntries(Model model){
        List<Entry> entries = entryService.getEntries();
        model.addAttribute("entries", entries);
        return "entries";
    }


    @GetMapping("/add/product")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add/product";
    }



    @GetMapping("/add/store")
    public String showAddStoreForm(Model model) {
        model.addAttribute("store", new Store());
        return "add/store";
    }
}