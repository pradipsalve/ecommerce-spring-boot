package com.Ecom.userservice;
import com.Ecom.DTO.OderItemDto;
import com.Ecom.DTO.OrderResponse;
import com.Ecom.Entity.CartItem;

import com.Ecom.Entity.Order;
import com.Ecom.Entity.Orderitem;
import com.Ecom.Entity.User;
import com.Ecom.Enum.Orderstatus;
import com.Ecom.userRepo.OrderRepository;
import com.Ecom.userRepo.UserRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Data
@Service
 @RequiredArgsConstructor
public class OrderServices {
    private final CartItemService cartItemService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public Optional<OrderResponse> createorder(String userid) {
        //validate for card
        List<CartItem> cartItems = cartItemService.getcard(userid);
        if (cartItems.isEmpty()) {

            return Optional.empty();

        }
        //validate for user
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userid));
        if (userOptional.isEmpty()) {

            return Optional.empty();
        }
        User user= userOptional.get();
        //calculate total price
        BigDecimal totalPrice= cartItems.stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        //create cart
        Order order= new Order();
        order.setUser(user);
        order.setOrderstatus(Orderstatus.CONFORM);
        order.setTotalAmount(totalPrice);
        List<Orderitem> orderitems= cartItems.stream()
                .map(item-> new Orderitem(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        order
                ))
                .toList();
                order.setItem(orderitems);
                Order saveOrder= orderRepository.save(order);

                //clear cart
        cartItemService.deletecart(userid);
        return Optional.of(mapToOrderResponse(saveOrder));
    }

    private OrderResponse mapToOrderResponse(Order saveOrder) {
        return new OrderResponse(
                saveOrder.getId(),
                saveOrder.getTotalAmount(),
                saveOrder.getOrderstatus(),
                saveOrder.getItem().stream()
                        .map(items -> new OderItemDto(
                                items.getId(),
                                items.getProduct().getId(),
                                items.getQuantity(),
                                items.getPrice(),
                                items.getPrice().multiply(
                                        BigDecimal.valueOf(items.getQuantity())
                                )
                        ))
                        .toList(),
                saveOrder.getCreatedat(),
                saveOrder.getUpdatedat()
        );
    }
}
