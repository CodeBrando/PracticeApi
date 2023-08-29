package com.example.practiceapi.models.mappers;

import com.example.practiceapi.models.bo.CategoryBO;
import com.example.practiceapi.models.bo.ProductBO;
import com.example.practiceapi.models.de.ProductDE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductMapper {

    public static ProductDE convertBOtoDE(ProductBO productBO){
        return ProductDE.builder()
                .id(Objects.isNull(productBO.getId()) ? null : productBO.getId())
                .name(productBO.getName())
                .price(productBO.getPrice())
                .productCode(productBO.getProductCode())
                .stock(productBO.getStock())
                .productCategories(CategoryMapper.convertBOListToDEList(productBO.getProductCategories()))
                .build();
    }

    public static ProductBO convertDEToBO(ProductDE productDE){
        return ProductBO.builder()
                .id(Objects.isNull(productDE.getId()) ? null : productDE.getId())
                .name(productDE.getName())
                .price(productDE.getPrice())
                .productCode(productDE.getProductCode())
                .stock(productDE.getStock())
                .productCategories(CategoryMapper.convertDEListToBOList(productDE.getProductCategories()))
                .build();
    }

    public static List<ProductDE> convertBOListToDEList(List<ProductBO> productsBO){
        List<ProductDE> productsDE = new ArrayList<>();
        productsBO.forEach(productBO -> productsDE.add(convertBOtoDE(productBO)));
        return productsDE;
    }

    public static List<ProductBO> convertDEListToBOList(List<ProductDE> productsDE){
        List<ProductBO> productsBO = new ArrayList<>();
        productsDE.forEach(productDE -> productsBO.add(convertDEToBO(productDE)));
        return productsBO;
    }
}
