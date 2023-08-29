package com.example.practiceapi.controllers.mappers;

import com.example.practiceapi.controllers.to.ProductTO;
import com.example.practiceapi.models.bo.ProductBO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductApiMapper {

    public static ProductBO convertTOToBO(ProductTO productTO){
        return ProductBO.builder()
                .id(Objects.isNull(productTO.getId()) ? null : productTO.getId())
                .name(productTO.getName())
                .price(productTO.getPrice())
                .productCode(productTO.getProductCode())
                .stock(productTO.getStock())
                .productCategories(CategoryApiMapper.convertTOListToBOList(productTO.getProductCategories()))
                .build();
    }

    public static ProductTO convertBOToTO(ProductBO productBO){
        return ProductTO.builder()
                .id(Objects.isNull(productBO.getId()) ? null : productBO.getId())
                .name(productBO.getName())
                .price(productBO.getPrice())
                .productCode(productBO.getProductCode())
                .stock(productBO.getStock())
                .productCategories(CategoryApiMapper.convertBOListToTOList(productBO.getProductCategories()))
                .build();
    }

    public static List<ProductBO> convertTOListToBOList(List<ProductTO> productsTO){
        List<ProductBO> productsBO = new ArrayList<>();
        productsTO.forEach(productTO -> productsBO.add(convertTOToBO(productTO)));
        return productsBO;
    }

    public static List<ProductTO> convertBOListToTOList(List<ProductBO> productsBO){
        List<ProductTO> productsTO = new ArrayList<>();
        productsBO.forEach(productBO -> productsTO.add(convertBOToTO(productBO)));
        return productsTO;
    }
}
