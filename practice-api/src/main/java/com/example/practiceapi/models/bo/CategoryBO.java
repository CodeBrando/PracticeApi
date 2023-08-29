package com.example.practiceapi.models.bo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryBO {

    private Long id;
    private String name;
    private List<ProductBO> products;

}
