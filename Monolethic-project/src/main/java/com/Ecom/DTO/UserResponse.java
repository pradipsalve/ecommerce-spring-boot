package com.Ecom.DTO;

import com.Ecom.Enum.UserRole;
import lombok.Data;

import java.util.Optional;


@Data
public class UserResponse {

    private  Long id;
    private String FirstName;
    private String LastName;
    private String Email;
    private  String phone;
    private UserRole role;
    private AddressDto address;


}
