package com.resellerapp.model.dto.home;

import com.resellerapp.model.enums.ConditionName;

import java.math.BigDecimal;

public class MyOffersDTO {

    private Long id;

    private ConditionName conditionName;

    private BigDecimal price;

    private String description;

    public ConditionName getConditionName() {
        return conditionName;
    }

    public MyOffersDTO setConditionName(ConditionName conditionName) {
        this.conditionName = conditionName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public MyOffersDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MyOffersDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getId() {
        return id;
    }

    public MyOffersDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
