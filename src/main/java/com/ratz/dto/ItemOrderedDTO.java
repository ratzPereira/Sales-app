package com.ratz.dto;

public class ItemOrderedDTO {

    private Integer product;
    private Integer quantity;

    public ItemOrderedDTO() {
    }

    public ItemOrderedDTO(Integer product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
