package com.Ecom.DTO;

import com.Ecom.Enum.UserRole;
import lombok.Data;

@Data
public class AddressDto {
    private String Street;
    private String City;
    private String State;
    private String Country;
    private String Zipcode;
}