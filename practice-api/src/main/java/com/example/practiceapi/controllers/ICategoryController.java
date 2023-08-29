package com.example.practiceapi.controllers;
import com.example.practiceapi.controllers.to.CategoryTO;
import com.example.practiceapi.controllers.to.ResponseTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;


@RequestMapping("api/v1/categories")
public interface ICategoryController {

    String APPLICATION_JSON = "application/json";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @Operation(summary = "Creates a new category")
    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> createCategory(@ApiParam(value = "Category to create", required = true)
                                             @Valid @RequestBody CategoryTO categoryTO);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @Operation(summary = "Finds a category")
    @GetMapping(value = "/{categoryName}", produces = APPLICATION_JSON)
    ResponseEntity<?> getCategoryByCategoryName(@ApiParam(value = "Category name of the category to find")
                                              @PathVariable("categoryName") String categoryName);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @Operation(summary = "Finds all categories")
    @GetMapping(produces = APPLICATION_JSON)
    ResponseEntity<?> getAllCategories();

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "UPDATED"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @Operation(summary = "Updates a category")
    @PutMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<?> updateCategory(@ApiParam(value = "Category to update", required = true)
                                    @Valid @RequestBody CategoryTO categoryTO, @PathVariable("categoryName") String categoryName);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @Operation(summary = "Deletes a category")
    @DeleteMapping(value = "/{categoryName}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> deleteCategory(@ApiParam(value = "Category name of the category to delete")
                                             @PathVariable("categoryName") String categoryName);


}




