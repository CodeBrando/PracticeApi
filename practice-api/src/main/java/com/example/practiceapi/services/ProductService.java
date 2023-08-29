package com.example.practiceapi.services;

import com.example.practiceapi.models.bo.ProductBO;
import com.example.practiceapi.models.de.ProductDE;
import com.example.practiceapi.models.mappers.ProductMapper;
import com.example.practiceapi.repo.IProductRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    IProductRepository repository;


    public void saveProduct(ProductBO productBO) throws EntityExistsException {

        if(repository.existsByProductCode(productBO.getProductCode())){
            throw new EntityExistsException("Product with product code: " + productBO.getProductCode() +  "already exists");
        }
        if(productBO.getStock() == null || productBO.getStock().equals("0")){
            productBO.setStock("Not in stock.");
        }
        if(productBO.getPrice() == null || productBO.getPrice().equals("0")){
            productBO.setPrice("Free.");
        } else {
            productBO.setPrice("$" + productBO.getPrice());
        }
        repository.save(ProductMapper.convertBOtoDE(productBO));
    }

    public ProductBO getProductByProductCode(String productCode) throws EntityNotFoundException{
      return ProductMapper.convertDEToBO(repository.findByProductCode(productCode)
              .orElseThrow(() -> new EntityNotFoundException("Product with code: " + productCode + "not found.")));
    }

    public void updateProduct(ProductBO productBO, String productCode){
        ProductBO productToUpdate = getProductByProductCode(productCode);
        productBO.setId(productToUpdate.getId());
        productBO.setProductCode(productToUpdate.getProductCode());
        repository.save(ProductMapper.convertBOtoDE(productBO));
    }

    public List<ProductBO> getAllProducts(){
        return ProductMapper.convertDEListToBOList(repository.findAll());
    }

    public void deleteProduct(String productCode){
        repository.delete(ProductMapper.convertBOtoDE(getProductByProductCode(productCode)));
    }
}
