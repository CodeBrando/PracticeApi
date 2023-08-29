package com.example.practiceapi.controllers.mappers;

import com.example.practiceapi.controllers.to.CategoryTO;
import com.example.practiceapi.models.bo.CategoryBO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryApiMapper {

    public static CategoryBO convertTOtoBO(CategoryTO categoryTO){
        return CategoryBO.builder()
                .id(Objects.isNull(categoryTO.getId()) ? null : categoryTO.getId())
                .name(categoryTO.getName())
                .products(ProductApiMapper.convertTOListToBOList(categoryTO.getProducts()))
                .build();
    }

    public static CategoryTO convertBOToTO(CategoryBO categoryBO){
        return CategoryTO.builder()
                .id(Objects.isNull(categoryBO) ? null : categoryBO.getId())
                .name(categoryBO.getName())
                .products(ProductApiMapper.convertBOListToTOList(categoryBO.getProducts()))
                .build();
    }

    public static List<CategoryBO> convertTOListToBOList(List<CategoryTO> categoriesTO){
        List<CategoryBO> categoriesBO = new ArrayList<>();
        categoriesTO.forEach(categoryTO -> categoriesBO.add(convertTOtoBO(categoryTO)));
        return categoriesBO;
    }

    public static List<CategoryTO> convertBOListToTOList(List<CategoryBO> categoriesBO){
        List<CategoryTO> categoriesTO = new ArrayList<>();
        categoriesBO.forEach(categoryBO -> categoriesTO.add(convertBOToTO(categoryBO)));
        return categoriesTO;
    }
}
