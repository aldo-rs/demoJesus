package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductTOResponse {

    private String productName;

    private String type;

    private LocalDate releaseDate;

    private int numberOfViews;

    private String abbreviation;

    private String categoryName;

    private boolean hasLength;

    private String lengthOfProduct;

}
