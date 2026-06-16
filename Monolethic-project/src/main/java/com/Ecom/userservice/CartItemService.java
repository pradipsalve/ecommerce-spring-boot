package com.Ecom.userservice;

import com.Ecom.DTO.CartItemRequest;
import com.Ecom.Entity.CartItem;
import com.Ecom.Entity.Product;
import com.Ecom.Entity.User;
import com.Ecom.userRepo.CartItemRepository;
import com.Ecom.userRepo.ProductRepository;
import com.Ecom.userRepo.UserRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class CartItemService {

    private  final ProductRepository productRepository;
    private  final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;

    public  boolean addtocart(String userid, CartItemRequest cartItemRequest){
        Optional<Product> productOpt= productRepository.findById(cartItemRequest.getId());
       // 1 if product is empty
        if(productOpt.isEmpty())
            return false;
        Product product =productOpt.get();

        // product quantity less than zero
        if(product.getStackquantity()<=0)
            return false;

        Optional <User> userOpt= userRepository.findById(Long.valueOf(userid));
       //2 user is Empty
        if(userOpt.isEmpty())
            return false;
        User user= userOpt.get();


       CartItem ExistingCartItem = cartItemRepository.findByUserAndProduct(user,product);

       if(ExistingCartItem!=null){
           //update cart

           ExistingCartItem.setQuantity(cartItemRequest.getQuantity());
           ExistingCartItem.setPrice(product.getPrice()
                   .multiply(BigDecimal.valueOf(ExistingCartItem.getQuantity())));
           cartItemRepository.save(ExistingCartItem);
       }
       else{
           //set card item
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItemRequest.getQuantity())));
            cartItemRepository.save(cartItem);


       }
       return  true;



    }

    public boolean Deletecart(String userid, Long id) {
        Optional<Product> productOpt= productRepository.findById(id);
        Optional <User> userOpt= userRepository.findById(Long.valueOf(userid));

        if(productOpt.isPresent() && userOpt.isPresent()){
            cartItemRepository.deleteByProductAndUser(productOpt.get(), userOpt.get());
            return  true;
        }
        return false;
    }

    public List<CartItem> getcard(String userId) {
       return (List<CartItem>) userRepository.findById(Long.valueOf(userId))
                .map(cartItemRepository::findByuser)
                .orElseGet(List::of);
    }

    public void deletecart(String userid) {
   userRepository.findById(Long.valueOf(userid)).ifPresent(
           cartItemRepository::deleteByUser);


    }
}
