package com.Ecom.usercontroller;

import com.Ecom.DTO.CartItemRequest;
import com.Ecom.Entity.CartItem;
import com.Ecom.Entity.Product;
import com.Ecom.userservice.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api")
public class CartItemController {

    private  final CartItemService cartItemService;
    @PostMapping("/cart")
    public ResponseEntity<String> Addcart(@RequestHeader ("X-USER-ID") String userid,
                                        @RequestBody CartItemRequest cartItemRequest){
        if(!cartItemService.addtocart(userid,cartItemRequest))
        return ResponseEntity.badRequest().body("product out of stock user not found or prodcut not fond");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> Deleteproductcart(@RequestHeader ("X-USER-ID") String userid,
                                                  @PathVariable Long id){
        if(!cartItemService.Deletecart(userid,id))
            return ResponseEntity.badRequest().build();

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/getcart")
    public ResponseEntity <List<CartItem>> getcart(@RequestHeader("X-USER-ID") String userId){
        return ResponseEntity.ok(cartItemService.getcard(userId));
    }
}

