package com.example.demo.dto;

import com.example.demo.database.model.Product;

import java.util.List;

public interface ProductMapper {

    Product ProductTOToProduct(AddProductTO dto);

    List<ProductTOResponse> getProductTOList(List<Product> productsList);

}
