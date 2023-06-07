package com.example.demo.Controller;

import com.example.demo.Model.Product;
import com.example.demo.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Repository
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ControllerProduct {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity  getAllProducts(){
        List<Product> products=productService.getAllProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping ("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product){
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Your Product has been Successfully added!");
    }

    @PutMapping ("/update/{id}")
    public ResponseEntity updateProduct(@Valid@RequestBody Product product, @PathVariable Integer id){
        productService.updateProduct(id,product);
        return ResponseEntity.status(200).body("Your Blog has been Successfully updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        productService.deleteBlog(id);
        return ResponseEntity.status(200).body("Sadly, Your Product Has been Deleted ");
    }

    @GetMapping("/get-id/{id}")
    public ResponseEntity findProductById(@PathVariable Integer id){
        Product product= productService.findProductById(id);
        return ResponseEntity.status(200).body(product);
    }
}

