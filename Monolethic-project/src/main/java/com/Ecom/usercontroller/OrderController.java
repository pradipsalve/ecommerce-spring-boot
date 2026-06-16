package com.Ecom.usercontroller;

import com.Ecom.DTO.OrderResponse;
import com.Ecom.userservice.OrderServices;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderServices orderServices;

@PostMapping
    public ResponseEntity<OrderResponse> creatOrder(@RequestHeader("X-USER-ID") String userid){
      return orderServices.createorder(userid)
              .map(orderResponse -> new ResponseEntity<>(orderResponse,HttpStatus.CREATED)
                      ).orElseGet(()->ResponseEntity.badRequest().build());


    }

}
