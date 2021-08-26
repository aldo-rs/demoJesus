package com.example.demo.service.impl;

import com.example.demo.database.ProductRepository;
import com.example.demo.database.model.Product;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Product add(Product product) {

        if(product.getAbbreviation()==null)
            product.setAbbreviation(generateAbbreviation(product.getName()));

        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Product edit(long id, Product product) {
        if(!productRepository.existsById(id))
            throw new IllegalStateException("the product does not exist ");

        product.setId(id);

        if(product.getAbbreviation()==null)
            product.setAbbreviation(generateAbbreviation(product.getName()));

        return productRepository.save(product);
    }

    @Override
    public void delete(long id) {
        if(!productRepository.existsById(id))
            throw new IllegalStateException("the product does not exist ");

        productRepository.deleteById(id);
    }

    @Override
    public List<Product> listByReleaseDateSortedDesc() {

        return productRepository.findProductLikeReleaseDateSortedDesc();
    }

    @Override
    public List<Product> listByReleaseDateSortedAsc() {

        return productRepository.findProductLikeReleaseDateSortedAsc();
    }

    @Override
    public List<Product> listByViewsSortedDesc() {

        return productRepository.findProductLikeViewsSortedDesc();
    }

    @Override
    public List<Product> listByViewsSortedAsc() {

        return productRepository.findProductLikeViewsSortedAsc();
    }

    private String generateAbbreviation(String productName){

        String[] words=productName.split(" ");
        String abbreviation="";

        if(words.length==1){
            return words[0].substring(0,3).toUpperCase();
        }else if(words.length==2){
            return words[0].substring(0,2).toUpperCase()+words[1].toUpperCase().charAt(0);
        }else {
            for(int i=0;i<words.length;i++){
                abbreviation=abbreviation+words[i].toUpperCase().charAt(0);
            }
        }

        return abbreviation;
    }

}
