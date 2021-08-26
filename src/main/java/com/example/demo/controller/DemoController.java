package com.example.demo.controller;

import com.example.demo.database.model.Product;
import com.example.demo.dto.AddProductTO;
import com.example.demo.dto.ProductMapper;
import com.example.demo.dto.ProductTOResponse;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/demo")
public class DemoController {

    private final ProductService productService;
    private final ProductMapper productMapper;


    @PostMapping("/product/add")
    public Product addProduct(@RequestBody AddProductTO addProductTO){

        return productService.add( productMapper.ProductTOToProduct(addProductTO) );
    }

    @PutMapping("/product/edit/{id}")
    public Product editProduct(@PathVariable long id,
                              @RequestBody AddProductTO addProductTO){

        return productService.edit( id, productMapper.ProductTOToProduct(addProductTO) );
    }

    @DeleteMapping("/product/delete/{id}")
    public void deleteProduct(@PathVariable long id){

        productService.delete( id );
    }

    @GetMapping("/product/listByReleasDateSortedDesc")
    public List<ProductTOResponse> productsListByReleasDateSortedDesc(){

        return productMapper.getProductTOList( productService.listByReleaseDateSortedDesc() );
    }

    @GetMapping("/product/listByReleasDateSortedAsc")
    public List<ProductTOResponse> productsListByReleasDateSortedAsc(){

        return productMapper.getProductTOList( productService.listByReleaseDateSortedAsc());
    }

    @GetMapping("/product/listByViewsSortedDesc")
    public List<ProductTOResponse> productsListByViewsSortedDesc(){

        return productMapper.getProductTOList( productService.listByViewsSortedDesc());
    }

    @GetMapping("/product/listByViewsSortedAsc")
    public List<ProductTOResponse> productsListByViewsSortedAsc(){

        return productMapper.getProductTOList( productService.listByViewsSortedAsc());
    }

}
