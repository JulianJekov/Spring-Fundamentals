package com.resellerapp.model.dto.home;

import com.resellerapp.model.enums.ConditionName;

import java.math.BigDecimal;

public class AllOtherOffersDTO {

    private Long id;

    private String createdByUsername;

    private String description;

    private String conditionName;

    private BigDecimal price;

    public String getCreatedByUsername() {
        return createdByUsername;
    }

    public AllOtherOffersDTO setCreatedByUsername(String createdByUsername) {
        this.createdByUsername = createdByUsername;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AllOtherOffersDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getConditionName() {
        return conditionName;
    }

    public AllOtherOffersDTO setConditionName(String conditionName) {
        this.conditionName = conditionName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AllOtherOffersDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AllOtherOffersDTO setId(Long id) {
        this.id = id;
        return this;
    }
}
