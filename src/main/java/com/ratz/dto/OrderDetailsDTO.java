package com.ratz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailsDTO {

    private Integer id;
    private String clientName;
    private BigDecimal total;
    private String orderDate;
    private String status;
    private List<ItemOrderedDetailsDTO> items;
}
