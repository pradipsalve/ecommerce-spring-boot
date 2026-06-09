package com.Ecom.DTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductRequest {

    private  Long id;
    private String name;
    private String discription;
    private BigDecimal price;
    private Boolean active;
    private Integer stackquantity;
    private  String categery;
    private String imageurl;



}
