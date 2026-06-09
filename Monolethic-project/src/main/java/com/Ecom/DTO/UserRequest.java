package com.Ecom.DTO;

import com.Ecom.Enum.UserRole;
import lombok.Data;

@Data
public class UserRequest {

    private String FirstName;
    private String LastName;
    private String Email;
    private  String phone;
    private AddressDto address;
}
