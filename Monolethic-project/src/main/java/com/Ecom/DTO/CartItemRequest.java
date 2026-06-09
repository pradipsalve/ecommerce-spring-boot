package com.Ecom.DTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemRequest {
    private Long id;
    private Integer quantity;
}
