package com.example.practiceapi.models.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductBO {
    private Long id;
    private String name;
    private String price;
    private String productCode;
    private String stock;
    private List<CategoryBO> productCategories;
}
