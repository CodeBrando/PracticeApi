package com.example.practiceapi.controllers;
import com.example.practiceapi.controllers.to.ProductTO;
import com.example.practiceapi.controllers.to.ResponseTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.text.ParsePosition;

@RequestMapping("api/v1/products")
public interface IProductController {

    String APPLICATION_JSON = "application/json";

    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Server Error")
    })
    @Operation(summary = "Creates a new product")
    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> createProduct(@ApiParam(value = "Product to create", required = true)
                                             @Valid @RequestBody ProductTO productTO);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @Operation(summary = "Finds a product")
    @GetMapping(value = "/{productCode}", produces = APPLICATION_JSON)
    ResponseEntity<?> getProductByProductCode(@ApiParam(value = "Product code of the product to find")
                                        @PathVariable("productCode") String productCode);

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @Operation(summary = "Finds all products")
    @GetMapping(produces = APPLICATION_JSON)
    ResponseEntity<?> getAllProducts();


    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "UPDATED"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @Operation(summary = "Updates a product")
    @PutMapping(value = "/{productCode}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<?> updateProduct(@ApiParam(value = "Product to update", required = true)
                                    @Valid @RequestBody ProductTO product, @PathVariable("productCode") String productCode);


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not Found"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @Operation(summary = "Deletes a product")
    @DeleteMapping(value = "/{productCode}", consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    ResponseEntity<ResponseTO> deleteProduct(@ApiParam(value = "Product code of the product to delete")
                                    @PathVariable("productCode") String productCode);
}
