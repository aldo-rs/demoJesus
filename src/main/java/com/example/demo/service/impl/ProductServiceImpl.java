package com.example.demo.service.impl;

import com.example.demo.database.ProductRepository;
import com.example.demo.database.model.Product;
import com.example.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        Stream<String> bloques= Pattern.compile(" ").splitAsStream(productName);
        long numberOfWords = bloques.count();

        System.out.println(numberOfWords);

        String res="";
        if(numberOfWords==1){
            bloques= Pattern.compile(" ").splitAsStream(productName);

            res= bloques.map(cadena -> cadena.substring(0, 3))
                    .collect(Collectors.toList())
                    .get(0);

            return res.toUpperCase();
        }else if(numberOfWords==2){
            bloques= Pattern.compile(" ").splitAsStream(productName);

            List<String> collect = bloques.map(cadena -> cadena.substring(0, 2))
                    .collect(Collectors.toList());

            res=collect.get(0) + collect.get(1).charAt(0);

            return res.toUpperCase();
        }

        bloques= Pattern.compile(" ").splitAsStream(productName);

        List<Character> iniciales = bloques.map(cadena -> cadena.charAt(0))
                .collect(Collectors.toList());

        for (char i : iniciales){
            res=res+=i;
        }

        return res.toUpperCase();
    }

}
