package com.example.springmodels.controllers;

import com.example.springmodels.models.Product;
import com.example.springmodels.models.Store;
import com.example.springmodels.models.TypeProduct;
import com.example.springmodels.repository.ProductRepository;
import com.example.springmodels.repository.StoreRepository;
import com.example.springmodels.repository.TypeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('MANAGER','ADMIN')")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TypeProductRepository typeProductRepository;
    @Autowired
    private StoreRepository storeRepository;

    @GetMapping("/product")
    public String productMain(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/product/add")
    public String productAdd(@ModelAttribute("products") Product product, Model type) {
        Iterable<TypeProduct> typeProduct = typeProductRepository.findAll();
        Iterable<Store> stores = storeRepository.findAll();
        type.addAttribute("typeProduct", typeProduct);
        type.addAttribute("stores", stores);
        return "product-add";
    }

    @PostMapping("/product/add")
    public String productPostAdd(@ModelAttribute("products") @Valid Product product,
                                 BindingResult bindingResult,
                                 @RequestParam String nameTypeProduct,
                                 @RequestParam String nameStore,
                                 Model type) {
        if (bindingResult.hasErrors()) {
            Iterable<TypeProduct> typeProduct = typeProductRepository.findAll();
            Iterable<Store> store = storeRepository.findAll();
            type.addAttribute("typeProduct", typeProduct);
            type.addAttribute("nameStore", store);
            return "product-add";
        }
        product.setTypeProduct(typeProductRepository.findByNameTypeProduct(nameTypeProduct));
        product.setStore(storeRepository.findByNameStore(nameStore));
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/product/{id}/edit")
    public String productDetails(@PathVariable(value = "id") long id, Model model, Model type)
    {
        Iterable<TypeProduct> typeProduct = typeProductRepository.findAll();
        Iterable<Store> stores = storeRepository.findAll();
        type.addAttribute("typeProduct", typeProduct);
        type.addAttribute("stores", stores);
        Product product = productRepository.findById(id).orElseThrow();
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping ("/product/{id}/edit")
    public  String productUpdate(@ModelAttribute("product") @Valid Product product,
                                 BindingResult bindingResult,
                                 @PathVariable(value = "id") long id)
    {
        if(bindingResult.hasErrors())
            return "product-edit";

//        TypeProduct typeProduct = typeProductRepository.findById(typeProductId).orElse(null);
//        typeProduct.setProducts(typeProduct);
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/product/{id}/remove")
    public  String productDelete(@PathVariable(value = "id") long id, Model model)
    {
        Product product = productRepository.findById(id).orElseThrow();
        productRepository.delete(product);
        return "redirect:/product";
    }

}

