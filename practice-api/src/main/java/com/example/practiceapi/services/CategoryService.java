package com.example.practiceapi.services;

import com.example.practiceapi.models.bo.CategoryBO;
import com.example.practiceapi.models.mappers.CategoryMapper;
import com.example.practiceapi.repo.ICategoryRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    ICategoryRepository repository;

    public void saveCategory(CategoryBO categoryBO) throws EntityExistsException {

        if(repository.existsByName(categoryBO.getName())){
            throw new EntityExistsException("Category with name: " + categoryBO.getName() + "already exists.");
        }
        repository.save(CategoryMapper.convertBOToDE(categoryBO));
    }

    public CategoryBO getCategoryByName(String name) throws EntityNotFoundException {
        return CategoryMapper.convertDEtoBO(repository.findByCategoryName(name)
                .orElseThrow(() -> new EntityNotFoundException("Category with name: " + name + "does not exist.")));

    }

    public List<CategoryBO> getAllCategories(){
        return CategoryMapper.convertDEListToBOList(repository.findAll());
    }

    public void updateCategory(CategoryBO categoryBO, String categoryName){
        CategoryBO categoryToUpdate = getCategoryByName(categoryName);
        categoryBO.setId(categoryToUpdate.getId());
        repository.save(CategoryMapper.convertBOToDE(categoryBO));
    }

    public void deleteCategory(String name){
        repository.delete(CategoryMapper.convertBOToDE(getCategoryByName(name)));
    }
}
