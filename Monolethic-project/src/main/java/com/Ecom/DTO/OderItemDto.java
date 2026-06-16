package com.Ecom.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
public class OderItemDto {
    private Long id;
    private Long productid;
    private  Integer Quantity;
    private BigDecimal price;
    private BigDecimal subtotal;


}
