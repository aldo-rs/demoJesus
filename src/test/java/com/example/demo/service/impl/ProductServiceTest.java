package com.example.demo.service.impl;

import com.example.demo.database.ProductRepository;
import com.example.demo.database.model.Category;
import com.example.demo.database.model.Product;
import com.example.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    ProductService underTest;


    @BeforeEach
    void setUp(){
        underTest=new ProductServiceImpl(productRepository);
    }


    @Test
    void canAddProduct() {
        //given
        Category category = Category.builder()
                .name("Trailer TV")
                .hasLength(true)
                .lengthOfProduct("50:00")
                .build();

        Product product = Product.builder()
                .name("New Things")
                .type("drama")
                .releaseDate(LocalDate.of(2020,9,25))
                .numberOfViews(3)
                .abbreviation("NET")
                .category(category)
                .insertDate(LocalDate.now())
                .build();

        //when
        underTest.add(product);

        //then
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        verify(productRepository).save(productArgumentCaptor.capture());

        Product capturedProduct = productArgumentCaptor.getValue();

        assertThat(capturedProduct).isEqualTo(product);
    }

    @Test
    void canGetlistByReleaseDateSortedDesc() {
        //when
        underTest.listByReleaseDateSortedDesc();

        //then
        verify(productRepository).findProductLikeReleaseDateSortedDesc();
    }

    @Test
    void canGetlistByReleaseDateSortedAsc() {
        //when
        underTest.listByReleaseDateSortedAsc();

        //then
        verify(productRepository).findProductLikeReleaseDateSortedAsc();
    }

    @Test
    void listByViewsSortedDesc() {
        //when
        underTest.listByViewsSortedDesc();

        //then
        verify(productRepository).findProductLikeViewsSortedDesc();
    }

    @Test
    void listByViewsSortedAsc() {
        //when
        underTest.listByViewsSortedAsc();

        //then
        verify(productRepository).findProductLikeViewsSortedAsc();
    }
}