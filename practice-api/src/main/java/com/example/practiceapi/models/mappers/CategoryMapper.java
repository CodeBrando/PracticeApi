package com.example.practiceapi.models.mappers;

import com.example.practiceapi.models.bo.CategoryBO;
import com.example.practiceapi.models.de.CategoryDE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoryMapper {

    public static CategoryBO convertDEtoBO(CategoryDE categoryDE){
        return CategoryBO.builder()
                .id(Objects.isNull(categoryDE.getId()) ? null : categoryDE.getId())
                .name(categoryDE.getName())
                .products(ProductMapper.convertDEListToBOList(categoryDE.getProducts()))
                .build();
    }

    public static CategoryDE convertBOToDE(CategoryBO categoryBO){
        return CategoryDE.builder()
                .id(Objects.isNull(categoryBO.getId()) ? null : categoryBO.getId())
                .name(categoryBO.getName())
                .products(ProductMapper.convertBOListToDEList(categoryBO.getProducts()))
                .build();
    }

    public static List<CategoryDE> convertBOListToDEList(List<CategoryBO> categoriesBO){
        List<CategoryDE> categoriesDE = new ArrayList<>();
        categoriesBO.forEach(categoryBO -> categoriesDE.add(convertBOToDE(categoryBO)));
        return categoriesDE;
    }

    public static List<CategoryBO> convertDEListToBOList(List<CategoryDE> categoriesDE){
        List<CategoryBO> categoriesBO = new ArrayList<>();
        categoriesDE.forEach(categoryDE -> categoriesBO.add(convertDEtoBO(categoryDE)));
        return categoriesBO;
    }
}
