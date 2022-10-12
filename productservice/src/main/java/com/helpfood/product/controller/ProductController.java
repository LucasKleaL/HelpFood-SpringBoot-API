package com.helpfood.product.controller;
import com.helpfood.product.entity.Product;
import com.helpfood.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import java.util.List;

@RestController
@RequestMapping("/produto")
@SecurityScheme(
        name = "Bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping
    public void salvar(@RequestBody Product product) {
        productService.salvar(product);
    }
    @DeleteMapping
    public void deletar(@RequestBody Product product) {
        productService.deletar(product.getId());
    }
    @GetMapping
    public List<Product> listar() {
        return productService.listar();
    }
}

