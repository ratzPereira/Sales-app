package com.ratz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemOrderedDTO {

    private Integer product;
    private Integer quantity;

}
