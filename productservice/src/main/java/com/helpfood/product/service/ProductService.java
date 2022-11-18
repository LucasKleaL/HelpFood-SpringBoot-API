package com.helpfood.product.service;

import com.helpfood.product.entity.Product;
import com.helpfood.product.repository.ProductRepository;
import com.helpfood.util.excecao.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void salvar(Product product) throws MessageException {
        if (product.getNome() == null || product.getNome().equals("") || product.getNome().length() > 55) {
            throw new MessageException("PRDCT_ERR001", "Invalid product data (nome).");
        }
        if (product.getTipo() == null || product.getTipo().equals("")) {
            throw new MessageException("PRDCT_ERR002", "Necessário informar um tipo de produto.");
        }
        if (product.getPeso() == null || product.getPeso().equals("")) {
            throw new MessageException("PRDCT_ERR003", "Necessário informar um peso");
        }
        if (product.getValidade() == null || product.getValidade().equals("")) {
            throw new MessageException("PRDCT_ERR004", "Necessário informar uma validade.");
        }
        productRepository.save(product);
    }

    public void deletar(int id) {
        productRepository.deleteById(id);
    }
    public List<Product> listar() {
        return productRepository.findAll();
    }

}