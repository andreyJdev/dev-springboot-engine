package ru.webapp.vinogradiya.utils;

import ru.webapp.vinogradiya.models.Product;

// модель с полями карточки одного товара
public class ProductCardDTO {
    private String name;
    private String description;
    private String image;

    public ProductCardDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.image = product.getImage();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
