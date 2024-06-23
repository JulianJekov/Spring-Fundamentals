package com.paintingscollectors.model.entity;

import com.paintingscollectors.model.enums.StyleName;

import javax.persistence.*;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private StyleName styleName;

    @Column(nullable = false)
    private String description;

    public Style() {
    }

    public Style(StyleName styleName, String description) {
        this.styleName = styleName;
        this.description = description;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public Style setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
