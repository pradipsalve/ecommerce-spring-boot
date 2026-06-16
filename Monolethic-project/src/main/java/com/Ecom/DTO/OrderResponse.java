package com.Ecom.DTO;

import com.Ecom.Enum.Orderstatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    private Orderstatus status;
    private List<OderItemDto>items;
    private LocalDateTime createdat;
    private LocalDateTime updatedat;
}
