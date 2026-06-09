package com.Ecom.Entity;


import com.Ecom.Enum.UserRole;
import jakarta.persistence.*;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;


@Entity
@Data
@RequiredArgsConstructor
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Long id;
    private String FirstName;
    private String LastName;
    private String Email;
    private  String phone;
    private UserRole role =UserRole.CUSTOMER;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "Address_id",referencedColumnName = "id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime CreatedAt;
    @UpdateTimestamp
    private  LocalDateTime updatedAt;
}
