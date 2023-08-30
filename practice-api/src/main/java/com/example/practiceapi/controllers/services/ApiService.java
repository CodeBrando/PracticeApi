package com.example.practiceapi.controllers.services;

import com.example.practiceapi.controllers.mappers.CategoryApiMapper;
import com.example.practiceapi.controllers.mappers.ProductApiMapper;
import com.example.practiceapi.controllers.to.CategoryTO;
import com.example.practiceapi.controllers.to.ProductTO;
import com.example.practiceapi.models.bo.CategoryBO;
import com.example.practiceapi.models.bo.ProductBO;
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

    public void assignCategory(String productCode, String categoryName){
        CategoryBO category = categoryService.getCategoryByName(categoryName);
        ProductBO product = productService.getProductByProductCode(productCode);
        product.getProductCategories().add(category);
    }

    public void assignProduct(String categoryName, String productCode){
        ProductBO product = productService.getProductByProductCode(productCode);
        CategoryBO category = categoryService.getCategoryByName(categoryName);
        category.getProducts().add(product);
    }
}
