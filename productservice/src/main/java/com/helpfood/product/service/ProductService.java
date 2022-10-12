package com.helpfood.product.service;

import com.helpfood.product.entity.Product;
import com.helpfood.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void salvar(Product product) {
        productRepository.save(product);
    }

    public void deletar(int id) {
        productRepository.deleteById(id);
    }
    public List<Product> listar() {
        return productRepository.findAll();
    }

}