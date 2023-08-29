package com.example.practiceapi.controllers;

import com.example.practiceapi.controllers.mappers.ProductApiMapper;
import com.example.practiceapi.controllers.services.ApiService;
import com.example.practiceapi.controllers.to.ProductTO;
import com.example.practiceapi.controllers.to.ResponseTO;
import com.example.practiceapi.services.ProductService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class ProductController implements IProductController{

    @Autowired
    ProductService productService;

    @Autowired
    ApiService apiService;

    @Override
    public ResponseEntity<ResponseTO> createProduct(ProductTO product){
        log.info("STARTING TO CREATE PRODUCT...");
        ResponseEntity<ResponseTO> response;
        try{
            product.setProductCode(UUID.randomUUID().toString());
            productService.saveProduct(ProductApiMapper.convertTOToBO(product));
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.CREATED.name()).build(), HttpStatus.CREATED);
            log.info("PRODUCT SUCCESSFULLY CREATED.");
        } catch (EntityExistsException | EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE CREATING PRODUCT: " + e);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> getProductByProductCode(String productCode){
        log.info("LOOKING FOR PRODUCT...");
        ResponseEntity<?> response;
        try{
            ProductTO product = ProductApiMapper.convertBOToTO(productService.getProductByProductCode(productCode));
            response = new ResponseEntity<>(product, HttpStatus.OK);
            log.info("PRODUCT FOUND SUCCESSFULLY.");
        } catch(EntityNotFoundException e){
            log.error("SOMETHING HAS GONE WRONG WHILE LOOKING FOR PRODUCT WITH PRODUCT CODE: " + productCode);
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> getAllProducts(){
        log.info("LOOKING FOR EVERY PRODUCT...");
        ResponseEntity<?> response;
        try{
            List<ProductTO> productList = ProductApiMapper.convertBOListToTOList(productService.getAllProducts());
            response = new ResponseEntity<>(productList, HttpStatus.OK);
        } catch(EntityNotFoundException e){
            log.error("NO PRODUCT HAS BEEN FOUND.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<?> updateProduct(ProductTO newProduct, String productCode){
        log.info("STARTING TO UPDATE PRODUCT...");
        ResponseEntity<?> response;
        try{
            productService.updateProduct(ProductApiMapper.convertTOToBO(newProduct), productCode);
            ProductTO updatedProduct = ProductApiMapper.convertBOToTO(productService.getProductByProductCode(productCode));
            log.info("PRODUCT SUCCESSFULLY UPDATED");
            response = new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch(EntityNotFoundException e){
            log.error("PRODUCT WITH PRODUCT CODE: " + productCode + "NOT FOUND.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @Override
    public ResponseEntity<ResponseTO> deleteProduct(String productCode){
        log.info("STARTING TO DELETE PRODUCT...");
        ResponseEntity<ResponseTO> response;
        try{
            productService.deleteProduct(productCode);
            log.info("PRODUCT WITH PRODUCT CODE: " + productCode + "SUCCESSFULLY DELETED.");
            response = new ResponseEntity<>(ResponseTO.builder().message(HttpStatus.OK.name()).build(), HttpStatus.OK);
        } catch(EntityNotFoundException e){
            log.info("PRODUCT WITH PRODUCT CODE: " + productCode + "NOT FOUND.");
            response = new ResponseEntity<>(ResponseTO.builder().message(e.getMessage()).build(), HttpStatus.NOT_FOUND);
        }
        return response;
    }
}
