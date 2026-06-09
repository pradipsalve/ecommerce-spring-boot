package com.Ecom.userRepo;

import com.Ecom.DTO.ProductResponse;
import com.Ecom.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findByActiveTrue();

    @Query("SELECT p FROM Product p WHERE p.active=true AND p.stackquantity > 0 AND LOWER (p.name) LIKE LOWER(CONCAT('%',:keyword, '%')) ")
    List<Product> searchproduct(@Param("keyword")String keyword);
}
