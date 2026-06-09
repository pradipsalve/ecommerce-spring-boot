package com.Ecom.userRepo;

import com.Ecom.Entity.CartItem;
import com.Ecom.Entity.Product;
import com.Ecom.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    CartItem findByUserAndProduct(User user, Product product);

    void deleteByProductAndUser(Product product, User user);
}
