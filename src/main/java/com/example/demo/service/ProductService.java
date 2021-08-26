package com.example.demo.service;

import com.example.demo.database.model.Product;

import java.util.List;

public interface ProductService {

    Product add (Product product);

    Product edit (long id, Product product);

    void delete(long id);

    List<Product> listByReleaseDateSortedDesc();

    List<Product> listByReleaseDateSortedAsc();

    List<Product> listByViewsSortedDesc();

    List<Product> listByViewsSortedAsc();

}
