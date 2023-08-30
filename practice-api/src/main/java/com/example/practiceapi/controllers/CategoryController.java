package com.example.practiceapi.controllers;


import com.example.practiceapi.controllers.mappers.CategoryApiMapper;
import com.example.practiceapi.controllers.mappers.ProductApiMapper;
import com.example.practiceapi.controllers.services.ApiService;
import com.example.practiceapi.controllers.to.CategoryTO;
import com.example.practiceapi.controllers.to.ProductTO;
import com.example.practiceapi.controllers.to.ResponseTO;
import com.example.practiceapi.services.CategoryService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
public class CategoryController implements ICategoryController{

    @Autowired
    ApiService apiService;

    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<ResponseTO> createCategory(CategoryTO categoryTO){
        log.info("STARTING TO CREATE CATEGORY...");
        ResponseEntity<ResponseTO> response;
        try{
            categoryService.saveCategory(CategoryApiMapper.convertTOtoBO(categoryTO));
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.CREATED.name()).build(), HttpStatus.CREATED);
            log.info("CATEGORY SUCCESSFULLY CREATED.");
        } catch (EntityExistsException | EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE CREATING NEW CATEGORY.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> assignProduct(String categoryName, String productCode){
        log.info("STARTING TO ASSIGN PRODUCT...");
        ResponseEntity<ResponseTO> response;
        try{
            apiService.assignProduct(categoryName, productCode);
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE ASSIGNING PRODUCT.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> getCategoryByCategoryName(String categoryName){
        log.info("LOOKING FOR CATEGORY...");
        ResponseEntity<?> response;
        try{
            CategoryTO category = CategoryApiMapper.convertBOToTO(categoryService.getCategoryByName(categoryName));
            response = new ResponseEntity<>(category, HttpStatus.OK);
            log.info("CATEGORY FOUND SUCCESSFULLY.");
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE LOOKING FOR CATEGORY WITH CATEGORY NAME: " + categoryName);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> getAllCategories(){
        log.info("LOOKING FOR EVERY CATEGORY...");
        ResponseEntity<?> response;
        try{
            List<CategoryTO> categoryList = CategoryApiMapper.convertBOListToTOList(categoryService.getAllCategories());
            response = new ResponseEntity<>(categoryList, HttpStatus.OK);
        } catch(EntityNotFoundException e){
            log.error("NO CATEGORY HAS BEEN FOUND.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> updateCategory(CategoryTO newCategory, String oldCategoryName){
        log.info("STARTING TO UPDATE CATEGORY...");
        ResponseEntity<?> response;
        try{
            categoryService.updateCategory(CategoryApiMapper.convertTOtoBO(newCategory), oldCategoryName);
            CategoryTO updatedCategory = CategoryApiMapper.convertBOToTO(categoryService.getCategoryByName(newCategory.getName()));
            log.info("PRODUCT SUCCESSFULLY UPDATED");
            response = new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        } catch(EntityNotFoundException e){
            log.error("CATEGORY WITH NAME: " + oldCategoryName + "NOT FOUND.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return response;
    }


    @Override
    public ResponseEntity<ResponseTO> deleteCategory(String categoryName){
        log.info("STARTING TO DELETE CATEGORY...");
        ResponseEntity<ResponseTO> response;
        try{
            categoryService.deleteCategory(categoryName);
            log.info("CATEGORY SUCCESSFULLY DELETED.");
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
        } catch(EntityNotFoundException e){
            log.info("CATEGORY WITH NAME: " + categoryName + "NOT FOUND.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
