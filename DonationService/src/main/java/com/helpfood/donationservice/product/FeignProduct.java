package com.helpfood.donationservice.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@FeignClient(name="productservice", decode404=true)
public interface FeignProduct {

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductTO> findProductById(@PathVariable("id") Integer productId);

}
