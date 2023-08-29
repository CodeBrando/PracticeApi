package com.example.practiceapi.models.de;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class ProductDE {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private String price;

    @Column(name = "PRODUCT_CODE")
    private String productCode;

    @Column(name = "STOCK")
    private String stock;

    @ManyToMany
    @JoinColumn(name = "PRODUCT_CATEGORY", referencedColumnName = "ID")
    private List<CategoryDE> productCategories;
}
