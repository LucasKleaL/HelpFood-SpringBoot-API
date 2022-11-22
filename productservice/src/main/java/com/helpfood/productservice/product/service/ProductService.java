package com.helpfood.productservice.product.service;

import com.helpfood.productservice.product.entity.Product;
import com.helpfood.productservice.product.repository.ProductRepository;
import com.helpfood.productservice.util.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) throws MessageException {
        if (product.getName() == null || product.getName().equals("") || product.getName().length() > 55) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (Name cannot be null).");
        }
        if (product.getType() == null || product.getType().equals("")) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (Type cannot be null).");
        }
        if (product.getWeight() == null || product.getWeight() == 0) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (Weight cannot be null).");
        }
        if (product.getValidityDate() == null || product.getValidityDate().equals("")) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (ValidityDate cannot be null).");
        }

        return productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product findById(Integer id) {
        return productRepository.findById(id).get();
    }

}