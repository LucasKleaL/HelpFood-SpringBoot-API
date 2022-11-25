package com.helpfood.productservice.product.service;

import com.helpfood.productservice.product.entity.Product;
import com.helpfood.productservice.product.repository.ProductRepository;
import com.helpfood.productservice.util.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) throws MessageException {
        // Name validation
        if (product.getName() == null || product.getName().equals("") || product.getName().length() > 55) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (Name cannot be null).");
        }
        // Type validation
        if (product.getType() == null || product.getType().equals("")) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (Type cannot be null).");
        }
        // Weight validation
        if (product.getWeight() == null || product.getWeight() == 0) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (Weight cannot be null).");
        }
        // ValidityDate validation
        if (product.getValidityDate() == null || product.getValidityDate().equals("")) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (ValidityDate cannot be null).");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            formatter.parse(product.getValidityDate());
        } catch (ParseException ex) {
            throw new MessageException("PRODUCT_ERR001", "Invalid product data (ValidityDate format must be 'dd/mm/yyyy').");
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