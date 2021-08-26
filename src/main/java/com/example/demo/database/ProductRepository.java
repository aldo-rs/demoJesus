package com.example.demo.database;

import com.example.demo.database.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select p from Product as p order by p.releaseDate desc")
    List<Product> findProductLikeReleaseDateSortedDesc();

    @Query(value = "select p from Product as p order by p.releaseDate asc")
    List<Product> findProductLikeReleaseDateSortedAsc();

    @Query(value = "select p from Product as p order by p.numberOfViews desc")
    List<Product> findProductLikeViewsSortedDesc();

    @Query(value = "select p from Product as p order by p.numberOfViews asc")
    List<Product> findProductLikeViewsSortedAsc();

}
