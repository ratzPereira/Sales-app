package com.ratz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemOrderedDetailsDTO {

    private String description;
    private BigDecimal unitPrice;
    private Integer quantity;
}
