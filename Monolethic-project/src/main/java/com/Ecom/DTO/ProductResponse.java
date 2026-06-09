package com.Ecom.DTO;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class ProductResponse {

    private  Long id;
    private String name;
    private String discription;
    private BigDecimal price;
    private Integer stackquantity;
    private  String categery;
    private String imageurl;
    private  Boolean active ;


}
