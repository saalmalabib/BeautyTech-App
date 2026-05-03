package com.loreal.skincare_api.Repository;

import com.loreal.skincare_api.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepo extends JpaRepository <Product, Long>{
    List<Product> findByTargetSkinType(String SkinType);

}
