package com.example.practiceapi.controllers.services;

import com.example.practiceapi.controllers.mappers.CategoryApiMapper;
import com.example.practiceapi.controllers.mappers.ProductApiMapper;
import com.example.practiceapi.controllers.to.CategoryTO;
import com.example.practiceapi.controllers.to.ProductTO;
import com.example.practiceapi.services.CategoryService;
import com.example.practiceapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    public void assignCategory(ProductTO productTO, String categoryName){
        CategoryTO category = CategoryApiMapper.convertBOToTO(categoryService.getCategoryByName(categoryName));
        productTO.getProductCategories().add(category);
    }

    public void assignProduct(CategoryTO categoryTO, String productCode){
        ProductTO product = ProductApiMapper.convertBOToTO(productService.getProductByProductCode(productCode));
        categoryTO.getProducts().add(product);
    }
}
