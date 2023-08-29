package com.example.practiceapi.controllers.to;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryTO {

    private Long id;

    @NotNull(message = "Category must have a name.")
    private String name;

    private List<ProductTO> products;
}
