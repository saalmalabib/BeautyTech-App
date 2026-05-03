package com.loreal.skincare_api.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String name;

    @Column(length = 1000)
    private String description;

   // @ElementCollection
    //private List<String> keyIngredients;

    private String targetSkinType; //oily, dry, sensitive

    private String category; //serum, moisturizer, cleanser

    @Column(name = "image_url")
    private String imageUrl;
}
