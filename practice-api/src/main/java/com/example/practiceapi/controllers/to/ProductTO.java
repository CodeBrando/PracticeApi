package com.example.practiceapi.controllers.to;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProductTO {

    private Long id;

    @NotNull(message = "Product must have a name.")
    @Size(message = "Product name must be at least 2 characters long.")
    private String name;

    private String price;

    @NotNull(message = "Product must have a product code.")
    @Size(message = "Product code must be at least 2 characters long.")
    private String productCode;

    private String stock;

    private List<CategoryTO> productCategories;
}
