package com.resellerapp.model.dto.home;

import java.math.BigDecimal;

public class BoughtOffersDTO {

    private String description;

    private BigDecimal price;

    public String getDescription() {
        return description;
    }

    public BoughtOffersDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BoughtOffersDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
