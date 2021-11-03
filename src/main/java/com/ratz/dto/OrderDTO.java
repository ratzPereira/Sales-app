package com.ratz.dto;

import com.ratz.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @NotNull(message = "The client id is missing")
    private Integer client;

    @NotNull(message = "The total price is missing")
    private BigDecimal total;

    @NotEmptyList
    private List<ItemOrderedDTO> itemList;

}
