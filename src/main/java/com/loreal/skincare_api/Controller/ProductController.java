package com.loreal.skincare_api.Controller;

import com.loreal.skincare_api.Entity.Product;
import com.loreal.skincare_api.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping()
public class ProductController {
    private final ProductService skincareService;

    public ProductController(ProductService skincareService) {
        this.skincareService = skincareService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return skincareService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> searchBySkin(@RequestParam String type){
        return skincareService.getProductsBySkinType(type);
    }

}
