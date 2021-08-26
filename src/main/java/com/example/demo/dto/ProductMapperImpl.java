package com.example.demo.dto;

import com.example.demo.database.model.Category;
import com.example.demo.database.model.Product;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper{


    @Override
    public Product ProductTOToProduct(AddProductTO dto) {

        String lengthOfProduct="";
        if(dto.isHasLength()){
            lengthOfProduct = lengthFormat( dto.getLengthOfProduct() );
        }

        Category category = Category.builder()
                .name(dto.getCategoryName())
                .hasLength(dto.isHasLength())
                .lengthOfProduct(lengthOfProduct)
                .build();

        Product product = Product.builder()
                .name(dto.getProductName())
                .type(dto.getType())
                .releaseDate(dto.getReleaseDate())
                .numberOfViews(dto.getNumberOfViews())
                .abbreviation(dto.getAbbreviation())
                .category(category)
                .insertDate(LocalDate.now())
                .build();

        return product;
    }

    @Override
    public List<ProductTOResponse> getProductTOList(List<Product> productsList) {

        List<ProductTOResponse> roductTOlist = new ArrayList<>();

        for(Product product : productsList)
            roductTOlist.add(ProductToProductTO(product));

        return roductTOlist;
    }

    private ProductTOResponse ProductToProductTO(Product product) {

        ProductTOResponse productTO = ProductTOResponse.builder()
                .productName(product.getName())
                .type(product.getType())
                .releaseDate(product.getReleaseDate())
                .numberOfViews(product.getNumberOfViews())
                .abbreviation(product.getAbbreviation())
                .categoryName(product.getCategory().getName())
                .hasLength(product.getCategory().isHasLength())
                .lengthOfProduct(product.getCategory().getLengthOfProduct())
                .build();

        return productTO;
    }

    private String lengthFormat(long lengthOfProduct){

        LocalTime resp=LocalTime.ofSecondOfDay(lengthOfProduct);
        DateTimeFormatter df=DateTimeFormatter.ofPattern("hh:mm:ss");

        if(lengthOfProduct<3600 && lengthOfProduct>=60) {
            df = DateTimeFormatter.ofPattern("mm:ss");
        }else if(lengthOfProduct<60 && lengthOfProduct>0)
            df = DateTimeFormatter.ofPattern("ss");

        return resp.format(df);
    }

}
