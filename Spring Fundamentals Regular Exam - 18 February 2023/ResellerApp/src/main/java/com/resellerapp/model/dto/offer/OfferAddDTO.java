package com.resellerapp.model.dto.offer;

import com.resellerapp.model.enums.ConditionName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferAddDTO {
    @Size(min = 2, max = 50, message = "Description length must be between 2 and 50 characters!")
    private String description;

    @Positive(message = "Price must be positive number!")
    @NotNull(message = "Price cannot be empty!")
    private BigDecimal price;

    @NotNull(message = "You must select a condition")
    private ConditionName name;

    public String getDescription() {
        return description;
    }

    public OfferAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ConditionName getName() {
        return name;
    }

    public OfferAddDTO setName(ConditionName name) {
        this.name = name;
        return this;
    }
}
