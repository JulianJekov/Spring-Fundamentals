package com.paintingscollectors.model.dto.painting;

import com.paintingscollectors.model.enums.StyleName;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AddPaintingDTO {

    @NotNull
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    private String name;

    @NotNull
    @Size(min = 5, max = 30, message = "Author length must be between 5 and 30 characters!")
    private String author;

    @NotBlank(message = "Image URL can not be empty!")
    @Size(max = 150, message = "Please enter valid image URL!")
    private String imageUrl;

    @NotNull(message = "You must select a style")
    private StyleName styleName;

    public AddPaintingDTO() {
    }

    public String getName() {

        return name;
    }

    public AddPaintingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public AddPaintingDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddPaintingDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public StyleName getStyleName() {
        return styleName;
    }

    public AddPaintingDTO setStyleName(StyleName styleName) {
        this.styleName = styleName;
        return this;
    }
}
