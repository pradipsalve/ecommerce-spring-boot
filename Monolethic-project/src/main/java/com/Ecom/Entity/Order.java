package com.Ecom.Entity;


import com.Ecom.Enum.Orderstatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Entity(name = "orders")
@Data
@NoArgsConstructor

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
  private User user;

  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  private Orderstatus orderstatus=Orderstatus.PENDING;

  @OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
  private List<Orderitem>item = new ArrayList<>();

  private LocalDateTime createdat;
  private LocalDateTime updatedat;


}
