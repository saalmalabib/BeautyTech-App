package com.loreal.skincare_api.Service;

import com.loreal.skincare_api.Entity.Product;
import com.loreal.skincare_api.ExceptionHandler.SkincareApiNotFoundException;
import com.loreal.skincare_api.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }


    public Product getApibyId(Long id) throws Throwable {
        return repo.findById(id)
                .orElseThrow(() -> new SkincareApiNotFoundException(id));
    }

    public List<Product> getProductsBySkinType(String SkinType){
        List<String> validTypes = List.of("Oily", "Dry", "Sensitive", "Combination");
        if (!validTypes.contains(SkinType)) {
            throw new IllegalArgumentException("Not a valid skin type");
        }
        return repo.findByTargetSkinType(SkinType);
    };

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

}
