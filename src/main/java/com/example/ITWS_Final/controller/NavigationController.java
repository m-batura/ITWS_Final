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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
@RequiredArgsConstructor
public class NavigationController {
    private final ProductService productService;
    private final StoreService storeService;
    private final EntryService entryService;

    Logger log
            = Logger.getLogger(
            NavigationController.class.getName());


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
    public String showEntries(@RequestParam(required = false) Integer productId, Model model){
        log.info("Filtering entries by productId: " + productId);
        List<Entry> entries = (productId != null)
                ? entryService.getEntriesByProduct(productService.getProductById(productId).orElse(null))
                : entryService.getEntries();

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

    @GetMapping("/add/entry")
    public String showAddEntryForm(Model model) {
        model.addAttribute("products", productService.getProducts());
        model.addAttribute("stores", storeService.getStores());
        model.addAttribute("entry", new Entry());
        return "add/entry";
    }

    @GetMapping("/edit/product/{id}")
    public String showEditProductForm(@PathVariable Integer id, Model model) {
        Product product = productService.getProductById(id).orElse(null);

        model.addAttribute("product", product); // Pass actual Product, not Optional
        return "edit/product";
    }

    @GetMapping("/edit/store/{id}")
    public String showEditStoreForm(@PathVariable Integer id, Model model) {
        Store store = storeService.getStoreById(id).orElse(null);

        model.addAttribute("store", store); // Pass actual Product, not Optional
        return "edit/store";
    }

    @GetMapping("/edit/entry/{id}")
    public String showEditEntryForm(@PathVariable Integer id, Model model) {
        model.addAttribute("products", productService.getProducts());
        model.addAttribute("stores", storeService.getStores());
        Entry entry = entryService.getEntryById(id).orElse(null);

        model.addAttribute("entry", entry); // Pass actual Product, not Optional
        return "edit/entry";
    }
}