package com.ratz.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTO {

    private Integer client;
    private BigDecimal total;
    private List<ItemOrderedDTO> itemList;

    public OrderDTO() {
    }

    public OrderDTO(Integer client, BigDecimal total, List<ItemOrderedDTO> itemList) {
        this.client = client;
        this.total = total;
        this.itemList = itemList;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<ItemOrderedDTO> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemOrderedDTO> itemList) {
        this.itemList = itemList;
    }
}
