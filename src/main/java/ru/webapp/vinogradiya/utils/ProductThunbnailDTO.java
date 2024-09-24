package ru.webapp.vinogradiya.utils;

import ru.webapp.vinogradiya.models.Product;

public class ProductThunbnailDTO {
    private Long id;

    private String name;

    private String image;

    public ProductThunbnailDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.image = product.getImage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
